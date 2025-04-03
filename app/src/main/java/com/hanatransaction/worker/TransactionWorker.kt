package com.hanatransaction.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.hanatransaction.HanaTransactionApp
import com.hanatransaction.data.api.RetrofitInstance
import com.hanatransaction.data.api.TransactionRequest
import com.hanatransaction.data.model.RepeatInterval
import com.hanatransaction.data.model.Transaction
import com.hanatransaction.data.repository.TransactionRepository
import com.hanatransaction.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Worker to handle processing of scheduled transactions
 */
class TransactionWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    
    private val transactionRepository: TransactionRepository = 
        (applicationContext as HanaTransactionApp).transactionRepository
    
    private val userRepository: UserRepository = 
        (applicationContext as HanaTransactionApp).userRepository
    
    private val apiService = RetrofitInstance.hanaBankApiService
    
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                // Get current user
                val user = userRepository.getCurrentUserSync() ?: return@withContext Result.failure()
                
                // Check if auth token is valid
                if (user.authToken == null || 
                    user.tokenExpiry == null || 
                    user.tokenExpiry < System.currentTimeMillis()) {
                    // Token is invalid or expired
                    return@withContext Result.retry()
                }
                
                // Get transactions that are due to be executed
                val now = Date()
                val dueTasks = transactionRepository.getTransactionsDueBy(now)
                
                if (dueTasks.isEmpty()) {
                    return@withContext Result.success()
                }
                
                // Process each transaction
                dueTasks.forEach { transaction ->
                    processTransaction(transaction, user.authToken)
                }
                
                Result.success()
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
    
    /**
     * Process an individual transaction
     */
    private suspend fun processTransaction(transaction: Transaction, authToken: String) {
        try {
            // Get current user
            val user = userRepository.getCurrentUserSync() ?: return
            
            // Create transaction request
            val request = TransactionRequest(
                sourceAccountNumber = user.accountNumber,
                destinationAccountNumber = transaction.accountNumber,
                destinationName = transaction.recipientName,
                amount = transaction.amount,
                currency = "KRW", // Default currency for Hana Bank
                memo = transaction.memo
            )
            
            // Execute the transaction
            val response = apiService.executeTransaction("Bearer ${authToken}", request)
            
            if (response.isSuccessful) {
                response.body()?.let { transactionResponse ->
                    // Mark transaction as completed
                    transactionRepository.markTransactionAsCompleted(
                        transaction.id,
                        transactionResponse.transactionId
                    )
                    
                    // If this is a recurring transaction, schedule the next one
                    if (transaction.repeatInterval != RepeatInterval.ONCE) {
                        scheduleNextTransaction(transaction)
                    }
                }
            }
        } catch (e: Exception) {
            // Transaction failed, will be retried on next worker execution
        }
    }
    
    /**
     * Schedule the next occurrence of a recurring transaction
     */
    private suspend fun scheduleNextTransaction(transaction: Transaction) {
        // Calculate next execution date
        val calendar = Calendar.getInstance()
        calendar.time = transaction.scheduledDate
        
        when (transaction.repeatInterval) {
            RepeatInterval.DAILY -> calendar.add(Calendar.DAY_OF_MONTH, 1)
            RepeatInterval.WEEKLY -> calendar.add(Calendar.WEEK_OF_YEAR, 1)
            RepeatInterval.MONTHLY -> calendar.add(Calendar.MONTH, 1)
            RepeatInterval.YEARLY -> calendar.add(Calendar.YEAR, 1)
            else -> return // No need to schedule for one-time transactions
        }
        
        val nextDate = calendar.time
        
        // Create new transaction object with updated date
        val nextTransaction = transaction.copy(
            id = 0, // Room will generate a new ID
            scheduledDate = nextDate,
            isCompleted = false,
            createdAt = Date(),
            lastExecutedAt = null,
            nextExecutionDate = null,
            transactionId = null
        )
        
        // Insert the next transaction
        transactionRepository.insertTransaction(nextTransaction)
    }
    
    companion object {
        private val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    }
} 
package com.hanatransaction.data.repository

import androidx.lifecycle.LiveData
import com.hanatransaction.data.dao.TransactionDao
import com.hanatransaction.data.model.RepeatInterval
import com.hanatransaction.data.model.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date

/**
 * Repository for handling transaction data operations
 */
class TransactionRepository(private val transactionDao: TransactionDao) {
    
    // Get all transactions
    val allTransactions: LiveData<List<Transaction>> = transactionDao.getAllTransactions()
    
    // Get pending transactions
    val pendingTransactions: LiveData<List<Transaction>> = transactionDao.getPendingTransactions()
    
    // Get recurring transactions
    val recurringTransactions: LiveData<List<Transaction>> = transactionDao.getRecurringTransactions()
    
    // Get recent completed transactions
    val recentCompletedTransactions: LiveData<List<Transaction>> = transactionDao.getRecentCompletedTransactions()
    
    /**
     * Insert a new transaction
     */
    suspend fun insertTransaction(transaction: Transaction): Long {
        return withContext(Dispatchers.IO) {
            // For recurring transactions, calculate the next execution date
            val transactionToInsert = if (transaction.repeatInterval != RepeatInterval.ONCE) {
                transaction.copy(nextExecutionDate = calculateNextExecutionDate(transaction.scheduledDate, transaction.repeatInterval))
            } else {
                transaction
            }
            
            transactionDao.insert(transactionToInsert)
        }
    }
    
    /**
     * Update an existing transaction
     */
    suspend fun updateTransaction(transaction: Transaction) {
        withContext(Dispatchers.IO) {
            transactionDao.update(transaction)
        }
    }
    
    /**
     * Delete a transaction
     */
    suspend fun deleteTransaction(transaction: Transaction) {
        withContext(Dispatchers.IO) {
            transactionDao.delete(transaction)
        }
    }
    
    /**
     * Get transaction by ID
     */
    suspend fun getTransactionById(id: Long): Transaction? {
        return withContext(Dispatchers.IO) {
            transactionDao.getTransactionById(id)
        }
    }
    
    /**
     * Get transactions that are due by a specific date
     */
    suspend fun getTransactionsDueBy(date: Date): List<Transaction> {
        return withContext(Dispatchers.IO) {
            transactionDao.getTransactionsDueBy(date)
        }
    }
    
    /**
     * Mark a transaction as completed
     */
    suspend fun markTransactionAsCompleted(transactionId: Long, transactionExternalId: String) {
        withContext(Dispatchers.IO) {
            val executionDate = Date()
            transactionDao.markAsCompleted(transactionId, executionDate, transactionExternalId)
            
            // Get the transaction to handle recurring transactions
            val transaction = transactionDao.getTransactionById(transactionId)
            
            // If this is a recurring transaction, create the next occurrence
            transaction?.let {
                if (it.repeatInterval != RepeatInterval.ONCE) {
                    val nextDate = calculateNextExecutionDate(it.scheduledDate, it.repeatInterval, it.lastExecutedAt)
                    
                    // Create a new transaction for the next occurrence
                    val nextTransaction = it.copy(
                        id = 0, // New ID will be generated
                        scheduledDate = nextDate,
                        isCompleted = false,
                        createdAt = Date(),
                        updatedAt = null,
                        lastExecutedAt = null,
                        nextExecutionDate = null,
                        transactionId = null
                    )
                    
                    transactionDao.insert(nextTransaction)
                }
            }
        }
    }
    
    /**
     * Calculate the next execution date based on the repeat interval
     */
    private fun calculateNextExecutionDate(
        baseDate: Date,
        repeatInterval: RepeatInterval,
        lastExecutionDate: Date? = null
    ): Date {
        val calendar = Calendar.getInstance()
        calendar.time = lastExecutionDate ?: baseDate
        
        when (repeatInterval) {
            RepeatInterval.ONCE -> return baseDate // No next date for one-time transactions
            RepeatInterval.DAILY -> calendar.add(Calendar.DAY_OF_MONTH, 1)
            RepeatInterval.WEEKLY -> calendar.add(Calendar.WEEK_OF_YEAR, 1)
            RepeatInterval.MONTHLY -> calendar.add(Calendar.MONTH, 1)
            RepeatInterval.YEARLY -> calendar.add(Calendar.YEAR, 1)
        }
        
        return calendar.time
    }
    
    /**
     * Clear all transactions 
     */
    suspend fun clearAllTransactions() {
        withContext(Dispatchers.IO) {
            transactionDao.clearAllTransactions()
        }
    }
    
    /**
     * Clear completed transactions
     */
    suspend fun clearCompletedTransactions() {
        withContext(Dispatchers.IO) {
            transactionDao.clearCompletedTransactions()
        }
    }
} 
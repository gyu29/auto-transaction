package com.hanatransaction.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

/**
 * Utility class for scheduling background work with WorkManager
 */
object WorkManagerUtil {
    
    private const val TRANSACTION_WORKER_TAG = "TRANSACTION_WORKER"
    private const val TRANSACTION_WORKER_NAME = "transaction_worker"
    
    /**
     * Schedule the transaction worker to run periodically
     */
    fun scheduleTransactionWorker(context: Context) {
        // Define constraints - require network connection
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        
        // Create a periodic work request to check for transactions every hour
        val transactionWorkRequest = PeriodicWorkRequestBuilder<TransactionWorker>(
            1, TimeUnit.HOURS // Check every hour
        )
            .setConstraints(constraints)
            .addTag(TRANSACTION_WORKER_TAG)
            .build()
        
        // Enqueue the work request
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            TRANSACTION_WORKER_NAME,
            ExistingPeriodicWorkPolicy.KEEP, // Keep existing if already scheduled
            transactionWorkRequest
        )
    }
    
    /**
     * Cancel the transaction worker
     */
    fun cancelTransactionWorker(context: Context) {
        WorkManager.getInstance(context).cancelUniqueWork(TRANSACTION_WORKER_NAME)
    }
} 
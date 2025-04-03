package com.hanatransaction.receiver

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.hanatransaction.HanaTransactionApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

/**
 * Broadcast receiver for handling transaction alarms
 */
class TransactionAlarmReceiver : BroadcastReceiver() {
    
    override fun onReceive(context: Context, intent: Intent) {
        val transactionId = intent.getLongExtra(EXTRA_TRANSACTION_ID, -1)
        
        if (transactionId != -1L) {
            // Launch coroutine to handle the transaction
            CoroutineScope(Dispatchers.IO).launch {
                val repository = (context.applicationContext as HanaTransactionApp).transactionRepository
                val transaction = repository.getTransactionById(transactionId)
                
                // Process the transaction
                transaction?.let {
                    // In a real app, this would trigger the transaction through the API
                    // and then update the local database
                    // For this demo, we're just marking it as completed
                    repository.markTransactionAsCompleted(it.id, "mock-transaction-id-${System.currentTimeMillis()}")
                }
            }
        }
    }
    
    companion object {
        private const val EXTRA_TRANSACTION_ID = "com.hanatransaction.EXTRA_TRANSACTION_ID"
        private const val REQUEST_CODE_PREFIX = 1000 // Base request code for transaction alarms
        
        /**
         * Schedule an alarm for a transaction
         */
        fun scheduleAlarm(context: Context, transactionId: Long, scheduledTime: Date) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TransactionAlarmReceiver::class.java).apply {
                putExtra(EXTRA_TRANSACTION_ID, transactionId)
            }
            
            // Create a unique request code for this transaction
            val requestCode = REQUEST_CODE_PREFIX + transactionId.toInt()
            
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            
            // Schedule the alarm
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    scheduledTime.time,
                    pendingIntent
                )
            } else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    scheduledTime.time,
                    pendingIntent
                )
            }
        }
        
        /**
         * Cancel an alarm for a transaction
         */
        fun cancelAlarm(context: Context, transactionId: Long) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TransactionAlarmReceiver::class.java)
            
            // Use the same request code as when scheduling
            val requestCode = REQUEST_CODE_PREFIX + transactionId.toInt()
            
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
            )
            
            // Cancel the alarm if it exists
            pendingIntent?.let {
                alarmManager.cancel(it)
                it.cancel()
            }
        }
    }
} 
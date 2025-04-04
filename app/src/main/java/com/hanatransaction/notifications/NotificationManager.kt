package com.hanatransaction.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.hanatransaction.R
import com.hanatransaction.data.model.Transaction
import com.hanatransaction.ui.MainActivity
import java.text.NumberFormat
import java.util.Locale

/**
 * Manages notifications for transaction alerts and payment reminders
 */
class NotificationManager(private val context: Context) {
    
    companion object {
        const val CHANNEL_ID_TRANSACTIONS = "channel_transactions"
        const val CHANNEL_ID_REMINDERS = "channel_reminders"
        
        const val NOTIFICATION_ID_TRANSACTION = 1001
        const val NOTIFICATION_ID_REMINDER = 2001
        
        const val PREF_TRANSACTION_ALERTS = "pref_transaction_alerts"
        const val PREF_PAYMENT_REMINDERS = "pref_payment_reminders"
    }
    
    private val sharedPreferences = context.getSharedPreferences(
        "hana_notification_prefs",
        Context.MODE_PRIVATE
    )
    
    init {
        createNotificationChannels()
    }
    
    /**
     * Create notification channels (required for Android O and above)
     */
    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val transactionChannel = NotificationChannel(
                CHANNEL_ID_TRANSACTIONS,
                context.getString(R.string.notification_channel_transactions),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = context.getString(R.string.notification_channel_transactions_desc)
            }
            
            val reminderChannel = NotificationChannel(
                CHANNEL_ID_REMINDERS,
                context.getString(R.string.notification_channel_reminders),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = context.getString(R.string.notification_channel_reminders_desc)
            }
            
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(transactionChannel)
            notificationManager.createNotificationChannel(reminderChannel)
        }
    }
    
    /**
     * Check if transaction alerts are enabled
     */
    fun areTransactionAlertsEnabled(): Boolean {
        return sharedPreferences.getBoolean(PREF_TRANSACTION_ALERTS, true)
    }
    
    /**
     * Set whether transaction alerts are enabled
     */
    fun setTransactionAlertsEnabled(enabled: Boolean) {
        sharedPreferences.edit()
            .putBoolean(PREF_TRANSACTION_ALERTS, enabled)
            .apply()
    }
    
    /**
     * Check if payment reminders are enabled
     */
    fun arePaymentRemindersEnabled(): Boolean {
        return sharedPreferences.getBoolean(PREF_PAYMENT_REMINDERS, true)
    }
    
    /**
     * Set whether payment reminders are enabled
     */
    fun setPaymentRemindersEnabled(enabled: Boolean) {
        sharedPreferences.edit()
            .putBoolean(PREF_PAYMENT_REMINDERS, enabled)
            .apply()
    }
    
    /**
     * Show a transaction alert notification
     */
    fun showTransactionAlert(transaction: Transaction) {
        if (!areTransactionAlertsEnabled()) return
        
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("transaction_id", transaction.id)
        }
        
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale.KOREA)
        val formattedAmount = currencyFormat.format(transaction.amount)
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID_TRANSACTIONS)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(context.getString(R.string.notification_transaction_title))
            .setContentText(context.getString(
                R.string.notification_transaction_text,
                formattedAmount,
                transaction.recipientName
            ))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        
        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID_TRANSACTION + transaction.id.toInt(), notification)
        }
    }
    
    /**
     * Show a payment reminder notification
     */
    fun showPaymentReminder(transaction: Transaction) {
        if (!arePaymentRemindersEnabled()) return
        
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("transaction_id", transaction.id)
        }
        
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale.KOREA)
        val formattedAmount = currencyFormat.format(transaction.amount)
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID_REMINDERS)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(context.getString(R.string.notification_reminder_title))
            .setContentText(context.getString(
                R.string.notification_reminder_text,
                formattedAmount,
                transaction.recipientName
            ))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        
        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID_REMINDER + transaction.id.toInt(), notification)
        }
    }
} 
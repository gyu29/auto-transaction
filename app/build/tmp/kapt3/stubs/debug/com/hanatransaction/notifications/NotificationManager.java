package com.hanatransaction.notifications;

import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.hanatransaction.R;
import com.hanatransaction.data.model.Transaction;
import com.hanatransaction.ui.MainActivity;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Manages notifications for transaction alerts and payment reminders
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\tJ\b\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\tJ\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\tJ\u000e\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/hanatransaction/notifications/NotificationManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "arePaymentRemindersEnabled", "", "areTransactionAlertsEnabled", "createNotificationChannels", "", "setPaymentRemindersEnabled", "enabled", "setTransactionAlertsEnabled", "showPaymentReminder", "transaction", "Lcom/hanatransaction/data/model/Transaction;", "showTransactionAlert", "Companion", "app_debug"})
public final class NotificationManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_ID_TRANSACTIONS = "channel_transactions";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_ID_REMINDERS = "channel_reminders";
    public static final int NOTIFICATION_ID_TRANSACTION = 1001;
    public static final int NOTIFICATION_ID_REMINDER = 2001;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_TRANSACTION_ALERTS = "pref_transaction_alerts";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_PAYMENT_REMINDERS = "pref_payment_reminders";
    private final android.content.SharedPreferences sharedPreferences = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.hanatransaction.notifications.NotificationManager.Companion Companion = null;
    
    public NotificationManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Create notification channels (required for Android O and above)
     */
    private final void createNotificationChannels() {
    }
    
    /**
     * Check if transaction alerts are enabled
     */
    public final boolean areTransactionAlertsEnabled() {
        return false;
    }
    
    /**
     * Set whether transaction alerts are enabled
     */
    public final void setTransactionAlertsEnabled(boolean enabled) {
    }
    
    /**
     * Check if payment reminders are enabled
     */
    public final boolean arePaymentRemindersEnabled() {
        return false;
    }
    
    /**
     * Set whether payment reminders are enabled
     */
    public final void setPaymentRemindersEnabled(boolean enabled) {
    }
    
    /**
     * Show a transaction alert notification
     */
    public final void showTransactionAlert(@org.jetbrains.annotations.NotNull()
    com.hanatransaction.data.model.Transaction transaction) {
    }
    
    /**
     * Show a payment reminder notification
     */
    public final void showPaymentReminder(@org.jetbrains.annotations.NotNull()
    com.hanatransaction.data.model.Transaction transaction) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/hanatransaction/notifications/NotificationManager$Companion;", "", "()V", "CHANNEL_ID_REMINDERS", "", "CHANNEL_ID_TRANSACTIONS", "NOTIFICATION_ID_REMINDER", "", "NOTIFICATION_ID_TRANSACTION", "PREF_PAYMENT_REMINDERS", "PREF_TRANSACTION_ALERTS", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
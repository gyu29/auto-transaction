package com.hanatransaction.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.hanatransaction.HanaTransactionApp;
import kotlinx.coroutines.Dispatchers;
import java.util.Date;

/**
 * Broadcast receiver for handling transaction alarms
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016\u00a8\u0006\n"}, d2 = {"Lcom/hanatransaction/receiver/TransactionAlarmReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "app_debug"})
public final class TransactionAlarmReceiver extends android.content.BroadcastReceiver {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String EXTRA_TRANSACTION_ID = "com.hanatransaction.EXTRA_TRANSACTION_ID";
    private static final int REQUEST_CODE_PREFIX = 1000;
    @org.jetbrains.annotations.NotNull()
    public static final com.hanatransaction.receiver.TransactionAlarmReceiver.Companion Companion = null;
    
    public TransactionAlarmReceiver() {
        super();
    }
    
    @java.lang.Override()
    public void onReceive(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u001e\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/hanatransaction/receiver/TransactionAlarmReceiver$Companion;", "", "()V", "EXTRA_TRANSACTION_ID", "", "REQUEST_CODE_PREFIX", "", "cancelAlarm", "", "context", "Landroid/content/Context;", "transactionId", "", "scheduleAlarm", "scheduledTime", "Ljava/util/Date;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Schedule an alarm for a transaction
         */
        public final void scheduleAlarm(@org.jetbrains.annotations.NotNull()
        android.content.Context context, long transactionId, @org.jetbrains.annotations.NotNull()
        java.util.Date scheduledTime) {
        }
        
        /**
         * Cancel an alarm for a transaction
         */
        public final void cancelAlarm(@org.jetbrains.annotations.NotNull()
        android.content.Context context, long transactionId) {
        }
    }
}
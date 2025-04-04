package com.hanatransaction.worker;

import android.content.Context;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.WorkManager;
import java.util.concurrent.TimeUnit;

/**
 * Utility class for scheduling background work with WorkManager
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/hanatransaction/worker/WorkManagerUtil;", "", "()V", "TRANSACTION_WORKER_NAME", "", "TRANSACTION_WORKER_TAG", "cancelTransactionWorker", "", "context", "Landroid/content/Context;", "scheduleTransactionWorker", "app_debug"})
public final class WorkManagerUtil {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TRANSACTION_WORKER_TAG = "TRANSACTION_WORKER";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TRANSACTION_WORKER_NAME = "transaction_worker";
    @org.jetbrains.annotations.NotNull()
    public static final com.hanatransaction.worker.WorkManagerUtil INSTANCE = null;
    
    private WorkManagerUtil() {
        super();
    }
    
    /**
     * Schedule the transaction worker to run periodically
     */
    public final void scheduleTransactionWorker(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    /**
     * Cancel the transaction worker
     */
    public final void cancelTransactionWorker(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}
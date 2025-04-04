package com.hanatransaction.worker;

import android.content.Context;
import androidx.work.CoroutineWorker;
import androidx.work.WorkerParameters;
import com.hanatransaction.HanaTransactionApp;
import com.hanatransaction.data.api.RetrofitInstance;
import com.hanatransaction.data.api.TransactionRequest;
import com.hanatransaction.data.model.RepeatInterval;
import com.hanatransaction.data.model.Transaction;
import com.hanatransaction.data.repository.TransactionRepository;
import com.hanatransaction.data.repository.UserRepository;
import kotlinx.coroutines.Dispatchers;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Worker to handle processing of scheduled transactions
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\r\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0082@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@\u00a2\u0006\u0002\u0010\u0018R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/hanatransaction/worker/TransactionWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "apiService", "Lcom/hanatransaction/data/api/HanaBankApiService;", "transactionRepository", "Lcom/hanatransaction/data/repository/TransactionRepository;", "userRepository", "Lcom/hanatransaction/data/repository/UserRepository;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processTransaction", "", "transaction", "Lcom/hanatransaction/data/model/Transaction;", "authToken", "", "(Lcom/hanatransaction/data/model/Transaction;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scheduleNextTransaction", "(Lcom/hanatransaction/data/model/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class TransactionWorker extends androidx.work.CoroutineWorker {
    @org.jetbrains.annotations.NotNull()
    private final com.hanatransaction.data.repository.TransactionRepository transactionRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hanatransaction.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hanatransaction.data.api.HanaBankApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.text.SimpleDateFormat DATE_FORMAT = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.hanatransaction.worker.TransactionWorker.Companion Companion = null;
    
    public TransactionWorker(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.work.WorkerParameters params) {
        super(null, null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> $completion) {
        return null;
    }
    
    /**
     * Process an individual transaction
     */
    private final java.lang.Object processTransaction(com.hanatransaction.data.model.Transaction transaction, java.lang.String authToken, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Schedule the next occurrence of a recurring transaction
     */
    private final java.lang.Object scheduleNextTransaction(com.hanatransaction.data.model.Transaction transaction, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/hanatransaction/worker/TransactionWorker$Companion;", "", "()V", "DATE_FORMAT", "Ljava/text/SimpleDateFormat;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
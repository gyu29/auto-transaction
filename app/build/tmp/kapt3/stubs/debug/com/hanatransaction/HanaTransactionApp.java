package com.hanatransaction;

import android.app.Application;
import androidx.work.Configuration;
import com.hanatransaction.data.AppDatabase;
import com.hanatransaction.data.repository.TransactionRepository;
import com.hanatransaction.data.repository.UserRepository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u00012\u00020\u0002:\u0001\u001aB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000f\u001a\u00020\u00108FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\t\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\u001b"}, d2 = {"Lcom/hanatransaction/HanaTransactionApp;", "Landroid/app/Application;", "Landroidx/work/Configuration$Provider;", "()V", "database", "Lcom/hanatransaction/data/AppDatabase;", "getDatabase", "()Lcom/hanatransaction/data/AppDatabase;", "database$delegate", "Lkotlin/Lazy;", "transactionRepository", "Lcom/hanatransaction/data/repository/TransactionRepository;", "getTransactionRepository", "()Lcom/hanatransaction/data/repository/TransactionRepository;", "transactionRepository$delegate", "userRepository", "Lcom/hanatransaction/data/repository/UserRepository;", "getUserRepository", "()Lcom/hanatransaction/data/repository/UserRepository;", "userRepository$delegate", "workManagerConfiguration", "Landroidx/work/Configuration;", "getWorkManagerConfiguration", "()Landroidx/work/Configuration;", "onCreate", "", "Companion", "app_debug"})
public final class HanaTransactionApp extends android.app.Application implements androidx.work.Configuration.Provider {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy database$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy transactionRepository$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy userRepository$delegate = null;
    private static com.hanatransaction.HanaTransactionApp instance;
    @org.jetbrains.annotations.NotNull()
    public static final com.hanatransaction.HanaTransactionApp.Companion Companion = null;
    
    public HanaTransactionApp() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hanatransaction.data.AppDatabase getDatabase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hanatransaction.data.repository.TransactionRepository getTransactionRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hanatransaction.data.repository.UserRepository getUserRepository() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public androidx.work.Configuration getWorkManagerConfiguration() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/hanatransaction/HanaTransactionApp$Companion;", "", "()V", "<set-?>", "Lcom/hanatransaction/HanaTransactionApp;", "instance", "getInstance", "()Lcom/hanatransaction/HanaTransactionApp;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.hanatransaction.HanaTransactionApp getInstance() {
            return null;
        }
    }
}
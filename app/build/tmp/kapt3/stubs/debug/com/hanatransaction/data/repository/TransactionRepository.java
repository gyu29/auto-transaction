package com.hanatransaction.data.repository;

import androidx.lifecycle.LiveData;
import com.hanatransaction.data.dao.TransactionDao;
import com.hanatransaction.data.model.RepeatInterval;
import com.hanatransaction.data.model.Transaction;
import kotlinx.coroutines.Dispatchers;
import java.util.Calendar;
import java.util.Date;

/**
 * Repository for handling transaction data operations
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J$\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0012H\u0002J\u000e\u0010\u0017\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u0018\u0010\u001e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001f\u001a\u00020 H\u0086@\u00a2\u0006\u0002\u0010!J\u001c\u0010\"\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010#\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010$J\u0016\u0010%\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u001e\u0010&\u001a\u00020\u00182\u0006\u0010\'\u001a\u00020 2\u0006\u0010(\u001a\u00020)H\u0086@\u00a2\u0006\u0002\u0010*J\u0016\u0010+\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u001dR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/hanatransaction/data/repository/TransactionRepository;", "", "transactionDao", "Lcom/hanatransaction/data/dao/TransactionDao;", "(Lcom/hanatransaction/data/dao/TransactionDao;)V", "allTransactions", "Landroidx/lifecycle/LiveData;", "", "Lcom/hanatransaction/data/model/Transaction;", "getAllTransactions", "()Landroidx/lifecycle/LiveData;", "pendingTransactions", "getPendingTransactions", "recentCompletedTransactions", "getRecentCompletedTransactions", "recurringTransactions", "getRecurringTransactions", "calculateNextExecutionDate", "Ljava/util/Date;", "baseDate", "repeatInterval", "Lcom/hanatransaction/data/model/RepeatInterval;", "lastExecutionDate", "clearAllTransactions", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearCompletedTransactions", "deleteTransaction", "transaction", "(Lcom/hanatransaction/data/model/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransactionById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransactionsDueBy", "date", "(Ljava/util/Date;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertTransaction", "markTransactionAsCompleted", "transactionId", "transactionExternalId", "", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTransaction", "app_debug"})
public final class TransactionRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.hanatransaction.data.dao.TransactionDao transactionDao = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.hanatransaction.data.model.Transaction>> allTransactions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.hanatransaction.data.model.Transaction>> pendingTransactions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.hanatransaction.data.model.Transaction>> recurringTransactions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.hanatransaction.data.model.Transaction>> recentCompletedTransactions = null;
    
    public TransactionRepository(@org.jetbrains.annotations.NotNull()
    com.hanatransaction.data.dao.TransactionDao transactionDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.hanatransaction.data.model.Transaction>> getAllTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.hanatransaction.data.model.Transaction>> getPendingTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.hanatransaction.data.model.Transaction>> getRecurringTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.hanatransaction.data.model.Transaction>> getRecentCompletedTransactions() {
        return null;
    }
    
    /**
     * Insert a new transaction
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertTransaction(@org.jetbrains.annotations.NotNull()
    com.hanatransaction.data.model.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    /**
     * Update an existing transaction
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateTransaction(@org.jetbrains.annotations.NotNull()
    com.hanatransaction.data.model.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Delete a transaction
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteTransaction(@org.jetbrains.annotations.NotNull()
    com.hanatransaction.data.model.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Get transaction by ID
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTransactionById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.hanatransaction.data.model.Transaction> $completion) {
        return null;
    }
    
    /**
     * Get transactions that are due by a specific date
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTransactionsDueBy(@org.jetbrains.annotations.NotNull()
    java.util.Date date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hanatransaction.data.model.Transaction>> $completion) {
        return null;
    }
    
    /**
     * Mark a transaction as completed
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object markTransactionAsCompleted(long transactionId, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionExternalId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Calculate the next execution date based on the repeat interval
     */
    private final java.util.Date calculateNextExecutionDate(java.util.Date baseDate, com.hanatransaction.data.model.RepeatInterval repeatInterval, java.util.Date lastExecutionDate) {
        return null;
    }
    
    /**
     * Clear all transactions
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearAllTransactions(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Clear completed transactions
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearCompletedTransactions(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}
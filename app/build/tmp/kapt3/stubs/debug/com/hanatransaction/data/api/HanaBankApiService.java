package com.hanatransaction.data.api;

import com.hanatransaction.data.model.Transaction;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * API service interface for the Hana Bank API
 * Note: This is a mock API service. In a real application, this would be implemented
 * to work with the actual Hana Bank API endpoints and data structures.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ(\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\rJ(\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\rJP\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u00062\b\b\u0001\u0010\u0012\u001a\u00020\u00062\b\b\u0001\u0010\u0013\u001a\u00020\u00062\b\b\u0001\u0010\u0014\u001a\u00020\u00152\b\b\u0001\u0010\u0016\u001a\u00020\u0015H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00032\b\b\u0001\u0010\u001a\u001a\u00020\u001bH\u00a7@\u00a2\u0006\u0002\u0010\u001cJ(\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u001f\u001a\u00020 H\u00a7@\u00a2\u0006\u0002\u0010!\u00a8\u0006\""}, d2 = {"Lcom/hanatransaction/data/api/HanaBankApiService;", "", "executeTransaction", "Lretrofit2/Response;", "Lcom/hanatransaction/data/api/TransactionResponse;", "token", "", "transactionRequest", "Lcom/hanatransaction/data/api/TransactionRequest;", "(Ljava/lang/String;Lcom/hanatransaction/data/api/TransactionRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccountBalance", "Lcom/hanatransaction/data/api/BalanceResponse;", "accountNumber", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getScheduledTransactions", "Lcom/hanatransaction/data/api/ScheduledTransactionsResponse;", "getTransactionHistory", "Lcom/hanatransaction/data/api/TransactionHistoryResponse;", "startDate", "endDate", "page", "", "size", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "Lcom/hanatransaction/data/api/AuthResponse;", "loginRequest", "Lcom/hanatransaction/data/api/LoginRequest;", "(Lcom/hanatransaction/data/api/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scheduleTransaction", "Lcom/hanatransaction/data/api/ScheduleResponse;", "scheduleRequest", "Lcom/hanatransaction/data/api/ScheduleTransactionRequest;", "(Ljava/lang/String;Lcom/hanatransaction/data/api/ScheduleTransactionRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface HanaBankApiService {
    
    /**
     * Authenticate user and get token
     */
    @retrofit2.http.POST(value = "auth/login")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.hanatransaction.data.api.LoginRequest loginRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.hanatransaction.data.api.AuthResponse>> $completion);
    
    /**
     * Get account balance
     */
    @retrofit2.http.GET(value = "accounts/{accountNumber}/balance")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAccountBalance(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "accountNumber")
    @org.jetbrains.annotations.NotNull()
    java.lang.String accountNumber, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.hanatransaction.data.api.BalanceResponse>> $completion);
    
    /**
     * Get transaction history
     */
    @retrofit2.http.GET(value = "accounts/{accountNumber}/transactions")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTransactionHistory(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Path(value = "accountNumber")
    @org.jetbrains.annotations.NotNull()
    java.lang.String accountNumber, @retrofit2.http.Query(value = "startDate")
    @org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @retrofit2.http.Query(value = "endDate")
    @org.jetbrains.annotations.NotNull()
    java.lang.String endDate, @retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.hanatransaction.data.api.TransactionHistoryResponse>> $completion);
    
    /**
     * Execute a transaction
     */
    @retrofit2.http.POST(value = "transactions/execute")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object executeTransaction(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.hanatransaction.data.api.TransactionRequest transactionRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.hanatransaction.data.api.TransactionResponse>> $completion);
    
    /**
     * Schedule a future transaction
     */
    @retrofit2.http.POST(value = "transactions/schedule")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object scheduleTransaction(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.hanatransaction.data.api.ScheduleTransactionRequest scheduleRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.hanatransaction.data.api.ScheduleResponse>> $completion);
    
    /**
     * Get scheduled transactions
     */
    @retrofit2.http.GET(value = "transactions/scheduled")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getScheduledTransactions(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Query(value = "accountNumber")
    @org.jetbrains.annotations.NotNull()
    java.lang.String accountNumber, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.hanatransaction.data.api.ScheduledTransactionsResponse>> $completion);
}
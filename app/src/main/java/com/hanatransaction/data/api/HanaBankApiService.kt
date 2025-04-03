package com.hanatransaction.data.api

import com.hanatransaction.data.model.Transaction
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * API service interface for the Hana Bank API
 * Note: This is a mock API service. In a real application, this would be implemented
 * to work with the actual Hana Bank API endpoints and data structures.
 */
interface HanaBankApiService {
    
    /**
     * Authenticate user and get token
     */
    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<AuthResponse>
    
    /**
     * Get account balance
     */
    @GET("accounts/{accountNumber}/balance")
    suspend fun getAccountBalance(
        @Header("Authorization") token: String,
        @Path("accountNumber") accountNumber: String
    ): Response<BalanceResponse>
    
    /**
     * Get transaction history
     */
    @GET("accounts/{accountNumber}/transactions")
    suspend fun getTransactionHistory(
        @Header("Authorization") token: String,
        @Path("accountNumber") accountNumber: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<TransactionHistoryResponse>
    
    /**
     * Execute a transaction
     */
    @POST("transactions/execute")
    suspend fun executeTransaction(
        @Header("Authorization") token: String,
        @Body transactionRequest: TransactionRequest
    ): Response<TransactionResponse>
    
    /**
     * Schedule a future transaction
     */
    @POST("transactions/schedule")
    suspend fun scheduleTransaction(
        @Header("Authorization") token: String,
        @Body scheduleRequest: ScheduleTransactionRequest
    ): Response<ScheduleResponse>
    
    /**
     * Get scheduled transactions
     */
    @GET("transactions/scheduled")
    suspend fun getScheduledTransactions(
        @Header("Authorization") token: String,
        @Query("accountNumber") accountNumber: String
    ): Response<ScheduledTransactionsResponse>
}

/**
 * Login request data class
 */
data class LoginRequest(
    val userId: String,
    val password: String,
    val deviceId: String
)

/**
 * Authentication response data class
 */
data class AuthResponse(
    val token: String,
    val expiresAt: Long,
    val user: UserInfo
)

/**
 * User information data class
 */
data class UserInfo(
    val userId: String,
    val name: String,
    val email: String?,
    val phoneNumber: String?,
    val accounts: List<AccountInfo>
)

/**
 * Account information data class
 */
data class AccountInfo(
    val accountNumber: String,
    val accountType: String,
    val currency: String
)

/**
 * Balance response data class
 */
data class BalanceResponse(
    val accountNumber: String,
    val balance: Double,
    val availableBalance: Double,
    val currency: String,
    val asOfDate: String
)

/**
 * Transaction history response data class
 */
data class TransactionHistoryResponse(
    val transactions: List<TransactionInfo>,
    val totalCount: Int,
    val page: Int,
    val size: Int
)

/**
 * Transaction information data class
 */
data class TransactionInfo(
    val transactionId: String,
    val date: String,
    val amount: Double,
    val description: String,
    val type: String,
    val counterparty: String,
    val counterpartyAccount: String,
    val status: String
)

/**
 * Transaction request data class
 */
data class TransactionRequest(
    val sourceAccountNumber: String,
    val destinationAccountNumber: String,
    val destinationName: String,
    val amount: Double,
    val currency: String = "KRW",
    val memo: String
)

/**
 * Transaction response data class
 */
data class TransactionResponse(
    val transactionId: String,
    val status: String,
    val processingDate: String,
    val fee: Double
)

/**
 * Schedule transaction request data class
 */
data class ScheduleTransactionRequest(
    val sourceAccountNumber: String,
    val destinationAccountNumber: String,
    val destinationName: String,
    val amount: Double,
    val currency: String = "KRW",
    val memo: String,
    val executionDate: String,
    val repeatInterval: String?, // null, "DAILY", "WEEKLY", "MONTHLY", "YEARLY"
    val endDate: String? // Only for recurring transactions
)

/**
 * Schedule response data class
 */
data class ScheduleResponse(
    val scheduleId: String,
    val status: String,
    val nextExecutionDate: String
)

/**
 * Scheduled transactions response data class
 */
data class ScheduledTransactionsResponse(
    val scheduledTransactions: List<ScheduledTransaction>
)

/**
 * Scheduled transaction data class
 */
data class ScheduledTransaction(
    val scheduleId: String,
    val sourceAccountNumber: String,
    val destinationAccountNumber: String,
    val destinationName: String,
    val amount: Double,
    val currency: String,
    val memo: String,
    val nextExecutionDate: String,
    val repeatInterval: String?,
    val endDate: String?
) 
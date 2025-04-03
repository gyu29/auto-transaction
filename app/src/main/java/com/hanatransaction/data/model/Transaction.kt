package com.hanatransaction.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Represents a bank transaction with scheduling capabilities
 */
@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    // Transaction details
    val amount: Double,
    val recipientName: String,
    val accountNumber: String,
    val memo: String,
    
    // Scheduling information
    val scheduledDate: Date,
    val repeatInterval: RepeatInterval,
    
    // Status tracking
    val isCompleted: Boolean = false,
    val createdAt: Date = Date(),
    val updatedAt: Date? = null,
    val lastExecutedAt: Date? = null,
    
    // For recurring transactions
    val nextExecutionDate: Date? = null,
    
    // Transaction identification in the bank system
    val transactionId: String? = null
)

/**
 * Defines the repeat interval for transactions
 */
enum class RepeatInterval {
    ONCE,       // One-time transaction
    DAILY,      // Every day
    WEEKLY,     // Every week
    MONTHLY,    // Every month
    YEARLY      // Every year
} 
package com.hanatransaction.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hanatransaction.data.model.Transaction
import java.util.Date

/**
 * Data Access Object for Transaction entities
 */
@Dao
interface TransactionDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction): Long
    
    @Update
    suspend fun update(transaction: Transaction)
    
    @Delete
    suspend fun delete(transaction: Transaction)
    
    @Query("SELECT * FROM transactions ORDER BY scheduledDate DESC")
    fun getAllTransactions(): LiveData<List<Transaction>>
    
    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getTransactionById(id: Long): Transaction?
    
    @Query("SELECT * FROM transactions WHERE isCompleted = 0 ORDER BY scheduledDate ASC")
    fun getPendingTransactions(): LiveData<List<Transaction>>
    
    @Query("SELECT * FROM transactions WHERE scheduledDate <= :date AND isCompleted = 0")
    suspend fun getTransactionsDueBy(date: Date): List<Transaction>
    
    @Query("SELECT * FROM transactions WHERE repeatInterval != 'ONCE' AND isCompleted = 0")
    fun getRecurringTransactions(): LiveData<List<Transaction>>
    
    @Query("SELECT * FROM transactions WHERE isCompleted = 1 ORDER BY lastExecutedAt DESC LIMIT 50")
    fun getRecentCompletedTransactions(): LiveData<List<Transaction>>
    
    @Query("UPDATE transactions SET isCompleted = 1, lastExecutedAt = :executionDate, transactionId = :transactionId WHERE id = :id")
    suspend fun markAsCompleted(id: Long, executionDate: Date, transactionId: String)
    
    @Query("UPDATE transactions SET nextExecutionDate = :nextDate WHERE id = :id")
    suspend fun updateNextExecutionDate(id: Long, nextDate: Date)
} 
package com.hanatransaction.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hanatransaction.data.model.User

/**
 * Data Access Object for User entities
 */
@Dao
interface UserDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)
    
    @Update
    suspend fun update(user: User)
    
    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: String): User?
    
    @Query("SELECT * FROM users LIMIT 1")
    fun getCurrentUser(): LiveData<User?>
    
    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getCurrentUserSync(): User?
    
    @Query("UPDATE users SET authToken = :token, tokenExpiry = :expiry WHERE id = :userId")
    suspend fun updateAuthToken(userId: String, token: String, expiry: Long)
    
    @Query("UPDATE users SET useBiometricAuth = :enabled WHERE id = :userId")
    suspend fun updateBiometricSetting(userId: String, enabled: Boolean)
    
    @Query("UPDATE users SET enableNotifications = :enabled WHERE id = :userId")
    suspend fun updateNotificationSetting(userId: String, enabled: Boolean)
    
    @Query("UPDATE users SET darkModeEnabled = :enabled WHERE id = :userId")
    suspend fun updateDarkModeSetting(userId: String, enabled: Boolean)
} 
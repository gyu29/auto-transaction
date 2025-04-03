package com.hanatransaction.data.repository

import androidx.lifecycle.LiveData
import com.hanatransaction.data.dao.UserDao
import com.hanatransaction.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for handling user data operations
 */
class UserRepository(private val userDao: UserDao) {
    
    // Get the current user (there should only be one in the app)
    val currentUser: LiveData<User?> = userDao.getCurrentUser()
    
    /**
     * Insert a new user
     */
    suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.insert(user)
        }
    }
    
    /**
     * Update an existing user
     */
    suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.update(user)
        }
    }
    
    /**
     * Get user by ID
     */
    suspend fun getUserById(id: String): User? {
        return withContext(Dispatchers.IO) {
            userDao.getUserById(id)
        }
    }
    
    /**
     * Get current user synchronously
     */
    suspend fun getCurrentUserSync(): User? {
        return withContext(Dispatchers.IO) {
            userDao.getCurrentUserSync()
        }
    }
    
    /**
     * Update the authentication token
     */
    suspend fun updateAuthToken(userId: String, token: String, expiry: Long) {
        withContext(Dispatchers.IO) {
            userDao.updateAuthToken(userId, token, expiry)
        }
    }
    
    /**
     * Update biometric authentication setting
     */
    suspend fun updateBiometricSetting(userId: String, enabled: Boolean) {
        withContext(Dispatchers.IO) {
            userDao.updateBiometricSetting(userId, enabled)
        }
    }
    
    /**
     * Update notification setting
     */
    suspend fun updateNotificationSetting(userId: String, enabled: Boolean) {
        withContext(Dispatchers.IO) {
            userDao.updateNotificationSetting(userId, enabled)
        }
    }
    
    /**
     * Update dark mode setting
     */
    suspend fun updateDarkModeSetting(userId: String, enabled: Boolean) {
        withContext(Dispatchers.IO) {
            userDao.updateDarkModeSetting(userId, enabled)
        }
    }
} 
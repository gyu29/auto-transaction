package com.hanatransaction.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.hanatransaction.security.AuthManager
import com.hanatransaction.security.PinManager
import java.util.concurrent.TimeUnit

/**
 * ViewModel responsible for authentication-related business logic
 */
class AuthViewModel(application: Application) : AndroidViewModel(application) {
    
    private val authManager = AuthManager(application)
    private val pinManager = PinManager(application)
    
    // Last time the user was authenticated
    private var lastAuthTime: Long = 0
    
    // Authentication timeout (10 minutes)
    private val AUTH_TIMEOUT_MINUTES = 10L
    
    /**
     * Check if the user needs to re-authenticate
     */
    fun requiresAuthentication(): Boolean {
        // If no PIN set, authentication not required
        if (!pinManager.isPinSet()) {
            return false
        }
        
        // If user not logged in, authentication required
        if (!authManager.isLoggedIn()) {
            return true
        }
        
        // If timeout has passed since last authentication, re-authentication required
        val currentTime = System.currentTimeMillis()
        return if (lastAuthTime > 0) {
            val diffMinutes = TimeUnit.MILLISECONDS.toMinutes(currentTime - lastAuthTime)
            diffMinutes >= AUTH_TIMEOUT_MINUTES
        } else {
            true
        }
    }
    
    /**
     * Update the last authentication time
     */
    fun updateAuthTime() {
        lastAuthTime = System.currentTimeMillis()
    }
    
    /**
     * Clear the last authentication time to force re-authentication
     */
    fun clearAuthTime() {
        lastAuthTime = 0
    }
    
    /**
     * Log the user in
     */
    fun login() {
        authManager.login()
        updateAuthTime()
    }
    
    /**
     * Log the user out
     */
    fun logout() {
        authManager.logout()
        lastAuthTime = 0
    }
} 
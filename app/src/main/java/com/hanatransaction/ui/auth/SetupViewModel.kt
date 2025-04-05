package com.hanatransaction.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hanatransaction.security.AuthManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel for the setup process
 * Handles verification of bank credentials and other setup-related logic
 */
class SetupViewModel(application: Application) : AndroidViewModel(application) {
    
    private val authManager = AuthManager(application)
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    
    private val _accountVerified = MutableLiveData<Boolean>()
    val accountVerified: LiveData<Boolean> = _accountVerified
    
    /**
     * Verify Hana Bank account credentials
     * In a real app, this would connect to the actual bank API
     */
    fun verifyAccount(username: String, password: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                
                // Simulated API call with delay
                val result = withContext(Dispatchers.IO) {
                    // Simulate network delay
                    delay(1500)
                    
                    // Simple validation for demo purposes
                    // In a real app, this would verify with the bank's API
                    validateCredentials(username, password)
                }
                
                if (result) {
                    // Store credentials in secure storage
                    authManager.login(username, password)
                    _accountVerified.value = true
                } else {
                    _error.value = "Invalid credentials. Please check and try again."
                }
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Perform simple validation of credentials
     * In a real app, this would be an actual API call
     */
    private fun validateCredentials(username: String, password: String): Boolean {
        // For demo purposes, accept any non-empty input that meets minimum requirements
        
        // Username should be at least 5 characters
        if (username.length < 5) {
            return false
        }
        
        // Password should be at least 8 characters and contain a digit
        if (password.length < 8 || !password.any { it.isDigit() }) {
            return false
        }
        
        return true
    }
    
    /**
     * Clear any error messages
     */
    fun clearError() {
        _error.value = null
    }
} 
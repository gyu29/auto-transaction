package com.hanatransaction.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanatransaction.HanaTransactionApp
import com.hanatransaction.data.model.Transaction
import com.hanatransaction.data.model.User
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    
    private val app = HanaTransactionApp.instance
    private val transactionRepository = app.transactionRepository
    private val userRepository = app.userRepository
    
    // LiveData for UI state
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user
    
    private val _balance = MutableLiveData<Double>()
    val balance: LiveData<Double> = _balance
    
    private val _pendingTransactions = MutableLiveData<List<Transaction>>()
    val pendingTransactions: LiveData<List<Transaction>> = _pendingTransactions
    
    private val _recentTransactions = MutableLiveData<List<Transaction>>()
    val recentTransactions: LiveData<List<Transaction>> = _recentTransactions
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    
    init {
        loadUserData()
        observeTransactions()
    }
    
    private fun loadUserData() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val currentUser = userRepository.getCurrentUserSync()
                _user.value = currentUser
                
                // In a real app, we would fetch the actual balance from the API
                _balance.value = 1000000.0 // Mock balance
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    private fun observeTransactions() {
        // Observe pending transactions
        transactionRepository.pendingTransactions.observeForever { transactions ->
            _pendingTransactions.value = transactions
        }
        
        // Observe recent completed transactions
        transactionRepository.recentCompletedTransactions.observeForever { transactions ->
            _recentTransactions.value = transactions
        }
    }
    
    fun refreshData() {
        loadUserData()
    }
    
    override fun onCleared() {
        super.onCleared()
        // Clean up any observers if needed
    }
} 
package com.hanatransaction.ui.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanatransaction.HanaTransactionApp
import com.hanatransaction.data.model.Transaction
import kotlinx.coroutines.launch

class TransactionsViewModel : ViewModel() {
    
    private val repository = HanaTransactionApp.instance.transactionRepository
    
    // LiveData for all transactions
    val allTransactions = repository.allTransactions
    
    // UI state management
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    
    private val _transactionsCleared = MutableLiveData<Boolean>()
    val transactionsCleared: LiveData<Boolean> = _transactionsCleared
    
    /**
     * Clear all transactions
     */
    fun clearAllTransactions() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                
                repository.clearAllTransactions()
                _transactionsCleared.value = true
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Clear only completed transactions
     */
    fun clearCompletedTransactions() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                
                repository.clearCompletedTransactions()
                _transactionsCleared.value = true
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Reset the transactions cleared flag
     */
    fun resetTransactionsClearedFlag() {
        _transactionsCleared.value = false
    }
} 
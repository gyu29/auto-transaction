package com.hanatransaction.ui.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanatransaction.HanaTransactionApp
import com.hanatransaction.data.model.Transaction
import kotlinx.coroutines.launch

class TransactionDetailViewModel : ViewModel() {
    
    private val repository = HanaTransactionApp.instance.transactionRepository
    
    private val _transaction = MutableLiveData<Transaction>()
    val transaction: LiveData<Transaction> = _transaction
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    
    private val _transactionCancelled = MutableLiveData<Boolean>()
    val transactionCancelled: LiveData<Boolean> = _transactionCancelled
    
    fun loadTransaction(transactionId: Long) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                
                val result = repository.getTransactionById(transactionId)
                _transaction.value = result
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun cancelTransaction() {
        val currentTransaction = _transaction.value ?: return
        
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                
                // Update the transaction to mark it as cancelled
                val updatedTransaction = currentTransaction.copy(
                    isCompleted = true,
                    updatedAt = java.util.Date()
                )
                
                repository.updateTransaction(updatedTransaction)
                _transaction.value = updatedTransaction
                _transactionCancelled.value = true
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
} 
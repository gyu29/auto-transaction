package com.hanatransaction.ui.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanatransaction.HanaTransactionApp
import com.hanatransaction.data.model.RepeatInterval
import com.hanatransaction.data.model.Transaction
import kotlinx.coroutines.launch
import java.util.Date

class NewTransactionViewModel : ViewModel() {
    
    private val app = HanaTransactionApp.instance
    private val transactionRepository = app.transactionRepository
    
    // Form data
    private val _recipientName = MutableLiveData<String>()
    val recipientName: LiveData<String> = _recipientName
    
    private val _accountNumber = MutableLiveData<String>()
    val accountNumber: LiveData<String> = _accountNumber
    
    private val _amount = MutableLiveData<String>()
    val amount: LiveData<String> = _amount
    
    private val _memo = MutableLiveData<String>()
    val memo: LiveData<String> = _memo
    
    private val _scheduledDate = MutableLiveData<Date>()
    val scheduledDate: LiveData<Date> = _scheduledDate
    
    private val _repeatInterval = MutableLiveData<RepeatInterval>()
    val repeatInterval: LiveData<RepeatInterval> = _repeatInterval
    
    // UI state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess
    
    init {
        // Set default values
        _scheduledDate.value = Date()
        _repeatInterval.value = RepeatInterval.ONCE
    }
    
    fun setRecipientName(name: String) {
        _recipientName.value = name
    }
    
    fun setAccountNumber(number: String) {
        _accountNumber.value = number
    }
    
    fun setAmount(amount: String) {
        _amount.value = amount
    }
    
    fun setMemo(memo: String) {
        _memo.value = memo
    }
    
    fun setScheduledDate(date: Date) {
        _scheduledDate.value = date
    }
    
    fun setRepeatInterval(interval: RepeatInterval) {
        _repeatInterval.value = interval
    }
    
    fun createTransaction() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                
                // Validate input
                if (!validateInput()) {
                    return@launch
                }
                
                // Create transaction
                val transaction = Transaction(
                    recipientName = _recipientName.value!!,
                    accountNumber = _accountNumber.value!!,
                    amount = _amount.value!!.toDouble(),
                    memo = _memo.value ?: "",
                    scheduledDate = _scheduledDate.value!!,
                    repeatInterval = _repeatInterval.value!!
                )
                
                // Save transaction
                transactionRepository.insertTransaction(transaction)
                
                _isSuccess.value = true
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    private fun validateInput(): Boolean {
        if (_recipientName.value.isNullOrBlank()) {
            _error.value = "Recipient name is required"
            return false
        }
        
        if (_accountNumber.value.isNullOrBlank()) {
            _error.value = "Account number is required"
            return false
        }
        
        if (_amount.value.isNullOrBlank()) {
            _error.value = "Amount is required"
            return false
        }
        
        try {
            _amount.value!!.toDouble()
        } catch (e: NumberFormatException) {
            _error.value = "Invalid amount"
            return false
        }
        
        if (_scheduledDate.value == null) {
            _error.value = "Scheduled date is required"
            return false
        }
        
        if (_repeatInterval.value == null) {
            _error.value = "Repeat interval is required"
            return false
        }
        
        return true
    }
    
    fun reset() {
        _recipientName.value = null
        _accountNumber.value = null
        _amount.value = null
        _memo.value = null
        _scheduledDate.value = Date()
        _repeatInterval.value = RepeatInterval.ONCE
        _error.value = null
        _isSuccess.value = false
    }
} 
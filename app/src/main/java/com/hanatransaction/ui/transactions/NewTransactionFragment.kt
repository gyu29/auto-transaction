package com.hanatransaction.ui.transactions

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hanatransaction.R
import com.hanatransaction.data.model.RepeatInterval
import com.hanatransaction.databinding.FragmentNewTransactionBinding
import com.hanatransaction.ui.base.AuthenticatedFragment
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class NewTransactionFragment : AuthenticatedFragment() {
    
    private var _binding: FragmentNewTransactionBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: NewTransactionViewModel by viewModels()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupClickListeners()
        observeViewModel()
    }
    
    private fun setupClickListeners() {
        // Date picker
        binding.buttonDate.setOnClickListener {
            showDatePicker()
        }
        
        // Repeat interval buttons
        binding.buttonOnce.setOnClickListener {
            viewModel.setRepeatInterval(RepeatInterval.ONCE)
            updateRepeatIntervalSelection()
        }
        
        binding.buttonDaily.setOnClickListener {
            viewModel.setRepeatInterval(RepeatInterval.DAILY)
            updateRepeatIntervalSelection()
        }
        
        binding.buttonWeekly.setOnClickListener {
            viewModel.setRepeatInterval(RepeatInterval.WEEKLY)
            updateRepeatIntervalSelection()
        }
        
        binding.buttonMonthly.setOnClickListener {
            viewModel.setRepeatInterval(RepeatInterval.MONTHLY)
            updateRepeatIntervalSelection()
        }
        
        binding.buttonYearly.setOnClickListener {
            viewModel.setRepeatInterval(RepeatInterval.YEARLY)
            updateRepeatIntervalSelection()
        }
        
        // Save button
        binding.buttonSave.setOnClickListener {
            saveTransaction()
        }
        
        // Cancel button
        binding.buttonCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.recipientName.observe(viewLifecycleOwner) { name ->
                binding.editTextRecipientName.setText(name)
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.accountNumber.observe(viewLifecycleOwner) { number ->
                binding.editTextAccountNumber.setText(number)
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.amount.observe(viewLifecycleOwner) { amount ->
                binding.editTextAmount.setText(amount)
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.memo.observe(viewLifecycleOwner) { memo ->
                binding.editTextMemo.setText(memo)
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.scheduledDate.observe(viewLifecycleOwner) { date ->
                binding.buttonDate.text = dateFormat.format(date)
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                binding.buttonSave.isEnabled = !isLoading
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.observe(viewLifecycleOwner) { error ->
                error?.let {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isSuccess.observe(viewLifecycleOwner) { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(
                        requireContext(),
                        R.string.transaction_scheduled,
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigateUp()
                }
            }
        }
    }
    
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        calendar.time = viewModel.scheduledDate.value ?: Date()
        
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                calendar.set(year, month, day)
                viewModel.setScheduledDate(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
    
    private fun updateRepeatIntervalSelection() {
        val interval = viewModel.repeatInterval.value ?: RepeatInterval.ONCE
        
        binding.buttonOnce.isSelected = interval == RepeatInterval.ONCE
        binding.buttonDaily.isSelected = interval == RepeatInterval.DAILY
        binding.buttonWeekly.isSelected = interval == RepeatInterval.WEEKLY
        binding.buttonMonthly.isSelected = interval == RepeatInterval.MONTHLY
        binding.buttonYearly.isSelected = interval == RepeatInterval.YEARLY
    }
    
    private fun saveTransaction() {
        viewLifecycleOwner.lifecycleScope.launch {
            // Require authentication before saving
            if (requireAuthentication()) {
                viewModel.apply {
                    setRecipientName(binding.editTextRecipientName.text.toString())
                    setAccountNumber(binding.editTextAccountNumber.text.toString())
                    setAmount(binding.editTextAmount.text.toString())
                    setMemo(binding.editTextMemo.text.toString())
                    createTransaction()
                }
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 
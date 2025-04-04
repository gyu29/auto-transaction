package com.hanatransaction.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.hanatransaction.R
import com.hanatransaction.databinding.FragmentTransactionDetailBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class TransactionDetailFragment : Fragment() {
    
    private var _binding: FragmentTransactionDetailBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: TransactionDetailViewModel by viewModels()
    private val args: TransactionDetailFragmentArgs by navArgs()
    
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val transactionId = args.transactionId
        if (transactionId == -1L) {
            findNavController().navigateUp()
            return
        }
        
        viewModel.loadTransaction(transactionId)
        observeViewModel()
        setupClickListeners()
    }
    
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.transaction.observe(viewLifecycleOwner) { transaction ->
                transaction?.let {
                    binding.textViewAmount.text = String.format("₩%,.0f", it.amount)
                    binding.textViewDescription.text = it.memo
                    binding.textViewDate.text = dateFormatter.format(it.scheduledDate)
                    binding.textViewStatus.text = if (it.isCompleted) "Completed" else "Pending"
                    binding.textViewCategory.text = it.recipientName
                    binding.textViewPaymentMethod.text = it.accountNumber
                    
                    // Show cancel button only for pending transactions
                    binding.buttonCancelTransaction.visibility = 
                        if (it.isCompleted) View.GONE else View.VISIBLE
                }
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.observe(viewLifecycleOwner) { error ->
                error?.let {
                    Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                }
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.transactionCancelled.observe(viewLifecycleOwner) { cancelled ->
                if (cancelled) {
                    Snackbar.make(
                        binding.root, 
                        getString(R.string.transaction_cancelled), 
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }
        
        binding.buttonCancelTransaction.setOnClickListener {
            showCancelConfirmationDialog()
        }
    }
    
    private fun showCancelConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.cancel_transaction)
            .setMessage(R.string.confirm_cancel)
            .setPositiveButton(R.string.yes) { _, _ ->
                viewModel.cancelTransaction()
            }
            .setNegativeButton(R.string.no, null)
            .show()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.hanatransaction.R
import com.hanatransaction.databinding.FragmentTransactionsBinding
import kotlinx.coroutines.launch

class TransactionsFragment : Fragment() {
    
    private var _binding: FragmentTransactionsBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: TransactionsViewModel by viewModels()
    private lateinit var transactionsAdapter: TransactionAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupListeners()
        observeViewModel()
    }
    
    private fun setupRecyclerView() {
        transactionsAdapter = TransactionAdapter { transaction ->
            navigateToTransactionDetail(transaction.id)
        }
        
        binding.recyclerViewTransactions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = transactionsAdapter
        }
    }
    
    private fun setupListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
        }
        
        binding.buttonClearTransactions.setOnClickListener {
            showClearTransactionsDialog()
        }
    }
    
    private fun showClearTransactionsDialog() {
        val items = arrayOf(
            getString(R.string.clear_completed_transactions),
            getString(R.string.clear_all_transactions)
        )
        
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.clear_transactions)
            .setItems(items) { _, which ->
                when (which) {
                    0 -> showConfirmDialog { viewModel.clearCompletedTransactions() }
                    1 -> showConfirmDialog { viewModel.clearAllTransactions() }
                }
            }
            .show()
    }
    
    private fun showConfirmDialog(clearAction: () -> Unit) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.clear_transactions)
            .setMessage(R.string.confirm_clear_transactions)
            .setPositiveButton(R.string.yes) { _, _ -> clearAction() }
            .setNegativeButton(R.string.no, null)
            .show()
    }
    
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allTransactions.observe(viewLifecycleOwner) { transactions ->
                transactionsAdapter.submitList(transactions)
                binding.textViewNoTransactions.visibility = 
                    if (transactions.isEmpty()) View.VISIBLE else View.GONE
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
            viewModel.transactionsCleared.observe(viewLifecycleOwner) { cleared ->
                if (cleared) {
                    Snackbar.make(
                        binding.root,
                        R.string.transactions_cleared,
                        Snackbar.LENGTH_LONG
                    ).show()
                    viewModel.resetTransactionsClearedFlag()
                }
            }
        }
    }
    
    private fun navigateToTransactionDetail(transactionId: Long) {
        val action = TransactionsFragmentDirections
            .actionTransactionsFragmentToTransactionDetailFragment(transactionId)
        findNavController().navigate(action)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 
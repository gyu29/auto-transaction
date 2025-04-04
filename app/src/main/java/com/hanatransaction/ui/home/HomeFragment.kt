package com.hanatransaction.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hanatransaction.R
import com.hanatransaction.databinding.FragmentHomeBinding
import com.hanatransaction.ui.transactions.TransactionAdapter
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var pendingTransactionsAdapter: TransactionAdapter
    private lateinit var recentTransactionsAdapter: TransactionAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerViews()
        setupClickListeners()
        observeViewModel()
    }
    
    private fun setupRecyclerViews() {
        // Setup pending transactions RecyclerView
        pendingTransactionsAdapter = TransactionAdapter { transaction ->
            navigateToTransactionDetail(transaction.id)
        }
        binding.recyclerViewPendingTransactions.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pendingTransactionsAdapter
        }
        
        // Setup recent transactions RecyclerView
        recentTransactionsAdapter = TransactionAdapter { transaction ->
            navigateToTransactionDetail(transaction.id)
        }
        binding.recyclerViewRecentTransactions.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recentTransactionsAdapter
        }
    }
    
    private fun setupClickListeners() {
        binding.fabNewTransaction.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newTransactionFragment)
        }
        
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshData()
            binding.swipeRefresh.isRefreshing = false
        }
    }
    
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.user.observe(viewLifecycleOwner) { user ->
                user?.let {
                    binding.textViewGreeting.text = getString(R.string.greeting, it.name)
                }
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.balance.observe(viewLifecycleOwner) { balance ->
                binding.textViewBalance.text = String.format("₩%,.0f", balance)
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pendingTransactions.observe(viewLifecycleOwner) { transactions ->
                pendingTransactionsAdapter.submitList(transactions)
                binding.textViewNoPendingTransactions.visibility = 
                    if (transactions.isEmpty()) View.VISIBLE else View.GONE
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.recentTransactions.observe(viewLifecycleOwner) { transactions ->
                recentTransactionsAdapter.submitList(transactions)
                binding.textViewNoRecentTransactions.visibility = 
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
                    // Show error message to user
                }
            }
        }
    }
    
    private fun navigateToTransactionDetail(transactionId: Long) {
        val bundle = Bundle().apply {
            putLong("transactionId", transactionId)
        }
        findNavController().navigate(R.id.action_homeFragment_to_transactionDetailFragment, bundle)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 
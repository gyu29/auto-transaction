package com.hanatransaction.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.hanatransaction.R
import com.hanatransaction.databinding.FragmentSettingsBinding
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {
    
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: SettingsViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupSwitches()
        setupButtons()
        observeViewModel()
    }
    
    private fun setupSwitches() {
        // Bind switches to ViewModel
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.useBiometric.observe(viewLifecycleOwner) { useBiometric ->
                binding.switchBiometric.isChecked = useBiometric
            }
            
            viewModel.darkMode.observe(viewLifecycleOwner) { darkMode ->
                binding.switchDarkMode.isChecked = darkMode
            }
            
            viewModel.useSystemTheme.observe(viewLifecycleOwner) { useSystemTheme ->
                binding.switchSystemTheme.isChecked = useSystemTheme
            }
            
            viewModel.transactionAlerts.observe(viewLifecycleOwner) { transactionAlerts ->
                binding.switchTransactionAlerts.isChecked = transactionAlerts
            }
            
            viewModel.paymentReminders.observe(viewLifecycleOwner) { paymentReminders ->
                binding.switchPaymentReminders.isChecked = paymentReminders
            }
            
            viewModel.requirePin.observe(viewLifecycleOwner) { requirePin ->
                binding.switchRequirePin.isChecked = requirePin
            }
        }
        
        // Set change listeners
        binding.switchBiometric.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setUseBiometric(isChecked)
            saveSettings()
        }
        
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setDarkMode(isChecked)
            binding.switchSystemTheme.isEnabled = !isChecked
            saveSettings()
        }
        
        binding.switchSystemTheme.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setUseSystemTheme(isChecked)
            saveSettings()
        }
        
        binding.switchTransactionAlerts.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setTransactionAlerts(isChecked)
            saveSettings()
        }
        
        binding.switchPaymentReminders.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setPaymentReminders(isChecked)
            saveSettings()
        }
        
        binding.switchRequirePin.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setRequirePin(isChecked)
            saveSettings()
        }
    }
    
    private fun setupButtons() {
        binding.buttonChangePassword.setOnClickListener {
            showChangePasswordDialog()
        }
        
        binding.buttonLogout.setOnClickListener {
            showLogoutConfirmationDialog()
        }
        
        binding.buttonChangePin.setOnClickListener {
            showChangePinDialog()
        }
        
        binding.buttonPrivacyPolicy.setOnClickListener {
            openWebPage("https://hanatransaction.com/privacy")
        }
        
        binding.buttonTermsOfService.setOnClickListener {
            openWebPage("https://hanatransaction.com/terms")
        }
    }
    
    private fun showChangePasswordDialog() {
        // In a real app, implement a proper password change dialog
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.settings_change_password)
            .setMessage("This functionality would be implemented with a proper password change form.")
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
    
    private fun showChangePinDialog() {
        // In a real app, implement a proper PIN change dialog
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.settings_change_pin)
            .setMessage("This functionality would be implemented with a proper PIN entry form.")
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
    
    private fun showLogoutConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.settings_logout)
            .setMessage("Are you sure you want to log out?")
            .setPositiveButton(R.string.yes) { _, _ ->
                // In a real app, implement actual logout functionality
                Snackbar.make(
                    binding.root,
                    "Logout functionality would be implemented here",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            .setNegativeButton(R.string.no, null)
            .show()
    }
    
    private fun openWebPage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            Snackbar.make(
                binding.root,
                "No browser available to open the URL",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
    
    private fun saveSettings() {
        viewModel.saveSettings()
    }
    
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.settingsSaved.observe(viewLifecycleOwner) { saved ->
                if (saved) {
                    Snackbar.make(
                        binding.root,
                        R.string.settings_saved,
                        Snackbar.LENGTH_SHORT
                    ).show()
                    viewModel.resetSettingsSavedFlag()
                }
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 
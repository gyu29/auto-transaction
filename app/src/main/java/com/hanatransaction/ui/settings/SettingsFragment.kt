package com.hanatransaction.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.hanatransaction.R
import com.hanatransaction.databinding.FragmentSettingsBinding
import com.hanatransaction.security.AuthManager
import com.hanatransaction.security.BiometricHelper
import com.hanatransaction.security.PinManager
import com.hanatransaction.ui.theme.ThemeManager
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {
    
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: SettingsViewModel by viewModels()
    private lateinit var authManager: AuthManager
    private lateinit var pinManager: PinManager
    private lateinit var biometricHelper: BiometricHelper
    private lateinit var themeManager: ThemeManager
    
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
        
        // Initialize managers
        authManager = AuthManager(requireContext())
        pinManager = PinManager(requireContext())
        biometricHelper = BiometricHelper(requireContext())
        themeManager = ThemeManager(requireContext())
        
        setupSwitches()
        setupButtons()
        observeViewModel()
    }
    
    private fun setupSwitches() {
        // Set initial switch states based on actual system settings
        binding.switchBiometric.isChecked = viewModel.useBiometric.value ?: false
        binding.switchDarkMode.isChecked = themeManager.isDarkModeEnabled()
        binding.switchSystemTheme.isChecked = themeManager.isUsingSystemTheme()
        binding.switchSystemTheme.isEnabled = !binding.switchDarkMode.isChecked
        binding.switchTransactionAlerts.isChecked = viewModel.transactionAlerts.value ?: true
        binding.switchPaymentReminders.isChecked = viewModel.paymentReminders.value ?: true
        
        // Set change listeners
        binding.switchBiometric.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && !biometricHelper.isBiometricAvailable()) {
                Snackbar.make(
                    binding.root,
                    R.string.biometric_not_available,
                    Snackbar.LENGTH_LONG
                ).show()
                binding.switchBiometric.isChecked = false
                viewModel.setUseBiometric(false)
            } else {
                viewModel.setUseBiometric(isChecked)
                saveSettings()
            }
        }
        
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setDarkMode(isChecked)
            themeManager.setDarkMode(isChecked)
            binding.switchSystemTheme.isEnabled = !isChecked
            if (isChecked) {
                binding.switchSystemTheme.isChecked = false
                viewModel.setUseSystemTheme(false)
                themeManager.setUseSystemTheme(false)
            }
            saveSettings()
        }
        
        binding.switchSystemTheme.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setUseSystemTheme(isChecked)
            themeManager.setUseSystemTheme(isChecked)
            if (isChecked) {
                binding.switchDarkMode.isChecked = false
                viewModel.setDarkMode(false)
                themeManager.setDarkMode(false)
            }
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
    }
    
    private fun setupButtons() {
        binding.buttonLogout.setOnClickListener {
            showLogoutConfirmationDialog()
        }
        
        binding.buttonChangePin.setOnClickListener {
            showChangePinDialog()
        }
        
        binding.buttonPrivacyPolicy.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_privacyPolicyFragment)
        }
        
        binding.buttonTermsOfService.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_termsOfServiceFragment)
        }
    }
    
    private fun showChangePinDialog() {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_change_pin, null)
        val currentPin = view.findViewById<EditText>(R.id.editTextCurrentPin)
        val newPin = view.findViewById<EditText>(R.id.editTextNewPin)
        val confirmPin = view.findViewById<EditText>(R.id.editTextConfirmPin)
        
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.settings_change_pin)
            .setView(view)
            .setPositiveButton(R.string.save) { _, _ ->
                val currentPinText = currentPin.text.toString()
                val newPinText = newPin.text.toString()
                val confirmPinText = confirmPin.text.toString()
                
                if (newPinText != confirmPinText) {
                    Snackbar.make(
                        binding.root,
                        R.string.pin_not_match,
                        Snackbar.LENGTH_LONG
                    ).show()
                    return@setPositiveButton
                }
                
                if (pinManager.changePin(currentPinText, newPinText)) {
                    Snackbar.make(
                        binding.root,
                        R.string.pin_changed,
                        Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    Snackbar.make(
                        binding.root,
                        R.string.pin_wrong,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }
    
    private fun showLogoutConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.settings_logout)
            .setMessage(R.string.logout_confirm)
            .setPositiveButton(R.string.yes) { _, _ ->
                // Perform actual logout
                authManager.logout()
                
                // Snackbar will not be seen as we're navigating away
                Snackbar.make(
                    binding.root,
                    R.string.logout_successful,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton(R.string.no, null)
            .show()
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
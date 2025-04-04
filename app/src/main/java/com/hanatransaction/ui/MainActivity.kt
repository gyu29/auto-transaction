package com.hanatransaction.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hanatransaction.R
import com.hanatransaction.databinding.ActivityMainBinding
import com.hanatransaction.security.AuthManager
import com.hanatransaction.security.PinManager
import com.hanatransaction.ui.auth.AuthViewModel
import com.hanatransaction.ui.auth.LoginActivity
import com.hanatransaction.ui.auth.PinFragmentDialog
import com.hanatransaction.worker.WorkManagerUtil

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var pinManager: PinManager
    private lateinit var authManager: AuthManager
    private lateinit var authViewModel: AuthViewModel
    
    private var wasInBackground = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize security managers
        pinManager = PinManager(this)
        authManager = AuthManager(this)
        
        // Initialize ViewModel
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        
        // If not logged in, redirect to login
        if (!authManager.isLoggedIn()) {
            startLoginActivity()
            finish()
            return
        }
        
        // Inflate layout using view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Set up navigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        
        // Set up bottom navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setupWithNavController(navController)
        
        // Schedule background work for processing transactions
        WorkManagerUtil.scheduleTransactionWorker(applicationContext)
    }
    
    override fun onResume() {
        super.onResume()
        
        // If returning from background, check authentication
        if (wasInBackground) {
            wasInBackground = false
            
            // Check if PIN verification is needed
            if (pinManager.isPinSet() && authViewModel.requiresAuthentication()) {
                // Either return to login activity or show PIN dialog
                startLoginActivity()
            }
        }
    }
    
    override fun onPause() {
        super.onPause()
        // Mark that we are going to background
        wasInBackground = true
        // Clear authentication state
        authViewModel.clearAuthTime()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    
    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
} 
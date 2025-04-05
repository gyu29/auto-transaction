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
import com.hanatransaction.worker.WorkManagerUtil

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var pinManager: PinManager
    private lateinit var authManager: AuthManager
    private lateinit var authViewModel: AuthViewModel
    
    private var firstLaunch = true
    private var wasInBackground = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize security managers
        pinManager = PinManager(this)
        authManager = AuthManager(this)
        
        // Initialize ViewModel
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        
        // Inflate layout using view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Check if user is authenticated
        checkAuthentication()
        
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
        
        // If returning from background or this isn't the first launch
        if (wasInBackground || !firstLaunch) {
            // Reset flag
            wasInBackground = false
            
            // Check authentication again
            checkAuthentication()
        }
        
        // Clear first launch flag after first resume
        firstLaunch = false
    }
    
    override fun onPause() {
        super.onPause()
        // Mark that we are going to background
        wasInBackground = true
        // Clear authentication state
        authViewModel.clearAuthTime()
    }
    
    private fun checkAuthentication() {
        // If not logged in or authentication timeout, go to login screen
        if (!authManager.isLoggedIn() || authViewModel.requiresAuthentication()) {
            startLoginActivityForResult()
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    
    private fun startLoginActivityForResult() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivityForResult(intent, LOGIN_REQUEST_CODE)
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        if (requestCode == LOGIN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Authentication successful, update auth time
                authViewModel.updateAuthTime()
            } else {
                // Authentication failed or cancelled, exit app
                finish()
            }
        }
    }
    
    companion object {
        private const val LOGIN_REQUEST_CODE = 1001
    }
} 
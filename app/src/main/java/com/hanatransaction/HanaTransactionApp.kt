package com.hanatransaction

import android.app.Application
import androidx.work.Configuration
import com.hanatransaction.data.AppDatabase
import com.hanatransaction.data.repository.TransactionRepository
import com.hanatransaction.data.repository.UserRepository

class HanaTransactionApp : Application(), Configuration.Provider {
    val database by lazy { AppDatabase.getDatabase(this) }
    val transactionRepository by lazy { TransactionRepository(database.transactionDao()) }
    val userRepository by lazy { UserRepository(database.userDao()) }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()
    
    companion object {
        lateinit var instance: HanaTransactionApp
            private set
    }
}
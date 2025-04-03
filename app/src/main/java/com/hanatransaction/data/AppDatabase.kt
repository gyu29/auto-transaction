package com.hanatransaction.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hanatransaction.data.dao.TransactionDao
import com.hanatransaction.data.dao.UserDao
import com.hanatransaction.data.model.Transaction
import com.hanatransaction.data.model.User
import com.hanatransaction.data.util.DateConverter

/**
 * Main database for the application
 */
@Database(
    entities = [Transaction::class, User::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun transactionDao(): TransactionDao
    abstract fun userDao(): UserDao
    
    companion object {
        // Singleton instance
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            // If the instance is not null, return it
            // If it is, create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "hana_transaction_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                
                INSTANCE = instance
                instance
            }
        }
    }
} 
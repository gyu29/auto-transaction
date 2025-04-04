package com.hanatransaction.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.hanatransaction.data.dao.TransactionDao;
import com.hanatransaction.data.dao.UserDao;
import com.hanatransaction.data.model.Transaction;
import com.hanatransaction.data.model.User;
import com.hanatransaction.data.util.DateConverter;

/**
 * Main database for the application
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\b"}, d2 = {"Lcom/hanatransaction/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "transactionDao", "Lcom/hanatransaction/data/dao/TransactionDao;", "userDao", "Lcom/hanatransaction/data/dao/UserDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.hanatransaction.data.model.Transaction.class, com.hanatransaction.data.model.User.class}, version = 1, exportSchema = false)
@androidx.room.TypeConverters(value = {com.hanatransaction.data.util.DateConverter.class})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.hanatransaction.data.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.hanatransaction.data.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.hanatransaction.data.dao.TransactionDao transactionDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.hanatransaction.data.dao.UserDao userDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/hanatransaction/data/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/hanatransaction/data/AppDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.hanatransaction.data.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}
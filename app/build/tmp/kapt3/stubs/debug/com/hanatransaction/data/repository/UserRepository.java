package com.hanatransaction.data.repository;

import androidx.lifecycle.LiveData;
import com.hanatransaction.data.dao.UserDao;
import com.hanatransaction.data.model.User;
import kotlinx.coroutines.Dispatchers;

/**
 * Repository for handling user data operations
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\n\u001a\u0004\u0018\u00010\u0007H\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\u0013J&\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J\u001e\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u001e\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u001e\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010 \u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\u0013R\u0019\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/hanatransaction/data/repository/UserRepository;", "", "userDao", "Lcom/hanatransaction/data/dao/UserDao;", "(Lcom/hanatransaction/data/dao/UserDao;)V", "currentUser", "Landroidx/lifecycle/LiveData;", "Lcom/hanatransaction/data/model/User;", "getCurrentUser", "()Landroidx/lifecycle/LiveData;", "getCurrentUserSync", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserById", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertUser", "", "user", "(Lcom/hanatransaction/data/model/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAuthToken", "userId", "token", "expiry", "", "(Ljava/lang/String;Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateBiometricSetting", "enabled", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateDarkModeSetting", "updateNotificationSetting", "updateUser", "app_debug"})
public final class UserRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.hanatransaction.data.dao.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.hanatransaction.data.model.User> currentUser = null;
    
    public UserRepository(@org.jetbrains.annotations.NotNull()
    com.hanatransaction.data.dao.UserDao userDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.hanatransaction.data.model.User> getCurrentUser() {
        return null;
    }
    
    /**
     * Insert a new user
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertUser(@org.jetbrains.annotations.NotNull()
    com.hanatransaction.data.model.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Update an existing user
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateUser(@org.jetbrains.annotations.NotNull()
    com.hanatransaction.data.model.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Get user by ID
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUserById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.hanatransaction.data.model.User> $completion) {
        return null;
    }
    
    /**
     * Get current user synchronously
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCurrentUserSync(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.hanatransaction.data.model.User> $completion) {
        return null;
    }
    
    /**
     * Update the authentication token
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateAuthToken(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String token, long expiry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Update biometric authentication setting
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateBiometricSetting(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Update notification setting
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateNotificationSetting(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Update dark mode setting
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateDarkModeSetting(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}
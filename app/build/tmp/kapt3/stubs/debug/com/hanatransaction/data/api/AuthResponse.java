package com.hanatransaction.data.api;

import com.hanatransaction.data.model.Transaction;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Authentication response data class
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0019"}, d2 = {"Lcom/hanatransaction/data/api/AuthResponse;", "", "token", "", "expiresAt", "", "user", "Lcom/hanatransaction/data/api/UserInfo;", "(Ljava/lang/String;JLcom/hanatransaction/data/api/UserInfo;)V", "getExpiresAt", "()J", "getToken", "()Ljava/lang/String;", "getUser", "()Lcom/hanatransaction/data/api/UserInfo;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class AuthResponse {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String token = null;
    private final long expiresAt = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.hanatransaction.data.api.UserInfo user = null;
    
    public AuthResponse(@org.jetbrains.annotations.NotNull()
    java.lang.String token, long expiresAt, @org.jetbrains.annotations.NotNull()
    com.hanatransaction.data.api.UserInfo user) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getToken() {
        return null;
    }
    
    public final long getExpiresAt() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hanatransaction.data.api.UserInfo getUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final long component2() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hanatransaction.data.api.UserInfo component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hanatransaction.data.api.AuthResponse copy(@org.jetbrains.annotations.NotNull()
    java.lang.String token, long expiresAt, @org.jetbrains.annotations.NotNull()
    com.hanatransaction.data.api.UserInfo user) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}
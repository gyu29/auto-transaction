package com.hanatransaction.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

/**
 * Defines the repeat interval for transactions
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/hanatransaction/data/model/RepeatInterval;", "", "(Ljava/lang/String;I)V", "ONCE", "DAILY", "WEEKLY", "MONTHLY", "YEARLY", "app_debug"})
public enum RepeatInterval {
    /*public static final*/ ONCE /* = new ONCE() */,
    /*public static final*/ DAILY /* = new DAILY() */,
    /*public static final*/ WEEKLY /* = new WEEKLY() */,
    /*public static final*/ MONTHLY /* = new MONTHLY() */,
    /*public static final*/ YEARLY /* = new YEARLY() */;
    
    RepeatInterval() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.hanatransaction.data.model.RepeatInterval> getEntries() {
        return null;
    }
}
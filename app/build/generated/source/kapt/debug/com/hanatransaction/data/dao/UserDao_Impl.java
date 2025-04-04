package com.hanatransaction.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.hanatransaction.data.model.User;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter<User> __updateAdapterOfUser;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAuthToken;

  private final SharedSQLiteStatement __preparedStmtOfUpdateBiometricSetting;

  private final SharedSQLiteStatement __preparedStmtOfUpdateNotificationSetting;

  private final SharedSQLiteStatement __preparedStmtOfUpdateDarkModeSetting;

  public UserDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `users` (`id`,`name`,`email`,`phoneNumber`,`accountNumber`,`bankCode`,`useBiometricAuth`,`enableNotifications`,`darkModeEnabled`,`authToken`,`tokenExpiry`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final User entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getEmail());
        }
        if (entity.getPhoneNumber() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPhoneNumber());
        }
        if (entity.getAccountNumber() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getAccountNumber());
        }
        if (entity.getBankCode() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getBankCode());
        }
        final int _tmp = entity.getUseBiometricAuth() ? 1 : 0;
        statement.bindLong(7, _tmp);
        final int _tmp_1 = entity.getEnableNotifications() ? 1 : 0;
        statement.bindLong(8, _tmp_1);
        final int _tmp_2 = entity.getDarkModeEnabled() ? 1 : 0;
        statement.bindLong(9, _tmp_2);
        if (entity.getAuthToken() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getAuthToken());
        }
        if (entity.getTokenExpiry() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getTokenExpiry());
        }
      }
    };
    this.__updateAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `users` SET `id` = ?,`name` = ?,`email` = ?,`phoneNumber` = ?,`accountNumber` = ?,`bankCode` = ?,`useBiometricAuth` = ?,`enableNotifications` = ?,`darkModeEnabled` = ?,`authToken` = ?,`tokenExpiry` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final User entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getEmail());
        }
        if (entity.getPhoneNumber() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPhoneNumber());
        }
        if (entity.getAccountNumber() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getAccountNumber());
        }
        if (entity.getBankCode() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getBankCode());
        }
        final int _tmp = entity.getUseBiometricAuth() ? 1 : 0;
        statement.bindLong(7, _tmp);
        final int _tmp_1 = entity.getEnableNotifications() ? 1 : 0;
        statement.bindLong(8, _tmp_1);
        final int _tmp_2 = entity.getDarkModeEnabled() ? 1 : 0;
        statement.bindLong(9, _tmp_2);
        if (entity.getAuthToken() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getAuthToken());
        }
        if (entity.getTokenExpiry() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getTokenExpiry());
        }
        if (entity.getId() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getId());
        }
      }
    };
    this.__preparedStmtOfUpdateAuthToken = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE users SET authToken = ?, tokenExpiry = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateBiometricSetting = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE users SET useBiometricAuth = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateNotificationSetting = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE users SET enableNotifications = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateDarkModeSetting = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE users SET darkModeEnabled = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final User user, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUser.insert(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final User user, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUser.handle(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAuthToken(final String userId, final String token, final long expiry,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateAuthToken.acquire();
        int _argIndex = 1;
        if (token == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, token);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, expiry);
        _argIndex = 3;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, userId);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateAuthToken.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateBiometricSetting(final String userId, final boolean enabled,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateBiometricSetting.acquire();
        int _argIndex = 1;
        final int _tmp = enabled ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, userId);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateBiometricSetting.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateNotificationSetting(final String userId, final boolean enabled,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateNotificationSetting.acquire();
        int _argIndex = 1;
        final int _tmp = enabled ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, userId);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateNotificationSetting.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateDarkModeSetting(final String userId, final boolean enabled,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateDarkModeSetting.acquire();
        int _argIndex = 1;
        final int _tmp = enabled ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, userId);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateDarkModeSetting.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getUserById(final String id, final Continuation<? super User> $completion) {
    final String _sql = "SELECT * FROM users WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<User>() {
      @Override
      @Nullable
      public User call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNumber");
          final int _cursorIndexOfAccountNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "accountNumber");
          final int _cursorIndexOfBankCode = CursorUtil.getColumnIndexOrThrow(_cursor, "bankCode");
          final int _cursorIndexOfUseBiometricAuth = CursorUtil.getColumnIndexOrThrow(_cursor, "useBiometricAuth");
          final int _cursorIndexOfEnableNotifications = CursorUtil.getColumnIndexOrThrow(_cursor, "enableNotifications");
          final int _cursorIndexOfDarkModeEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "darkModeEnabled");
          final int _cursorIndexOfAuthToken = CursorUtil.getColumnIndexOrThrow(_cursor, "authToken");
          final int _cursorIndexOfTokenExpiry = CursorUtil.getColumnIndexOrThrow(_cursor, "tokenExpiry");
          final User _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPhoneNumber;
            if (_cursor.isNull(_cursorIndexOfPhoneNumber)) {
              _tmpPhoneNumber = null;
            } else {
              _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
            }
            final String _tmpAccountNumber;
            if (_cursor.isNull(_cursorIndexOfAccountNumber)) {
              _tmpAccountNumber = null;
            } else {
              _tmpAccountNumber = _cursor.getString(_cursorIndexOfAccountNumber);
            }
            final String _tmpBankCode;
            if (_cursor.isNull(_cursorIndexOfBankCode)) {
              _tmpBankCode = null;
            } else {
              _tmpBankCode = _cursor.getString(_cursorIndexOfBankCode);
            }
            final boolean _tmpUseBiometricAuth;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfUseBiometricAuth);
            _tmpUseBiometricAuth = _tmp != 0;
            final boolean _tmpEnableNotifications;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfEnableNotifications);
            _tmpEnableNotifications = _tmp_1 != 0;
            final boolean _tmpDarkModeEnabled;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfDarkModeEnabled);
            _tmpDarkModeEnabled = _tmp_2 != 0;
            final String _tmpAuthToken;
            if (_cursor.isNull(_cursorIndexOfAuthToken)) {
              _tmpAuthToken = null;
            } else {
              _tmpAuthToken = _cursor.getString(_cursorIndexOfAuthToken);
            }
            final Long _tmpTokenExpiry;
            if (_cursor.isNull(_cursorIndexOfTokenExpiry)) {
              _tmpTokenExpiry = null;
            } else {
              _tmpTokenExpiry = _cursor.getLong(_cursorIndexOfTokenExpiry);
            }
            _result = new User(_tmpId,_tmpName,_tmpEmail,_tmpPhoneNumber,_tmpAccountNumber,_tmpBankCode,_tmpUseBiometricAuth,_tmpEnableNotifications,_tmpDarkModeEnabled,_tmpAuthToken,_tmpTokenExpiry);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<User> getCurrentUser() {
    final String _sql = "SELECT `users`.`id` AS `id`, `users`.`name` AS `name`, `users`.`email` AS `email`, `users`.`phoneNumber` AS `phoneNumber`, `users`.`accountNumber` AS `accountNumber`, `users`.`bankCode` AS `bankCode`, `users`.`useBiometricAuth` AS `useBiometricAuth`, `users`.`enableNotifications` AS `enableNotifications`, `users`.`darkModeEnabled` AS `darkModeEnabled`, `users`.`authToken` AS `authToken`, `users`.`tokenExpiry` AS `tokenExpiry` FROM users LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"users"}, false, new Callable<User>() {
      @Override
      @Nullable
      public User call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfName = 1;
          final int _cursorIndexOfEmail = 2;
          final int _cursorIndexOfPhoneNumber = 3;
          final int _cursorIndexOfAccountNumber = 4;
          final int _cursorIndexOfBankCode = 5;
          final int _cursorIndexOfUseBiometricAuth = 6;
          final int _cursorIndexOfEnableNotifications = 7;
          final int _cursorIndexOfDarkModeEnabled = 8;
          final int _cursorIndexOfAuthToken = 9;
          final int _cursorIndexOfTokenExpiry = 10;
          final User _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPhoneNumber;
            if (_cursor.isNull(_cursorIndexOfPhoneNumber)) {
              _tmpPhoneNumber = null;
            } else {
              _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
            }
            final String _tmpAccountNumber;
            if (_cursor.isNull(_cursorIndexOfAccountNumber)) {
              _tmpAccountNumber = null;
            } else {
              _tmpAccountNumber = _cursor.getString(_cursorIndexOfAccountNumber);
            }
            final String _tmpBankCode;
            if (_cursor.isNull(_cursorIndexOfBankCode)) {
              _tmpBankCode = null;
            } else {
              _tmpBankCode = _cursor.getString(_cursorIndexOfBankCode);
            }
            final boolean _tmpUseBiometricAuth;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfUseBiometricAuth);
            _tmpUseBiometricAuth = _tmp != 0;
            final boolean _tmpEnableNotifications;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfEnableNotifications);
            _tmpEnableNotifications = _tmp_1 != 0;
            final boolean _tmpDarkModeEnabled;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfDarkModeEnabled);
            _tmpDarkModeEnabled = _tmp_2 != 0;
            final String _tmpAuthToken;
            if (_cursor.isNull(_cursorIndexOfAuthToken)) {
              _tmpAuthToken = null;
            } else {
              _tmpAuthToken = _cursor.getString(_cursorIndexOfAuthToken);
            }
            final Long _tmpTokenExpiry;
            if (_cursor.isNull(_cursorIndexOfTokenExpiry)) {
              _tmpTokenExpiry = null;
            } else {
              _tmpTokenExpiry = _cursor.getLong(_cursorIndexOfTokenExpiry);
            }
            _result = new User(_tmpId,_tmpName,_tmpEmail,_tmpPhoneNumber,_tmpAccountNumber,_tmpBankCode,_tmpUseBiometricAuth,_tmpEnableNotifications,_tmpDarkModeEnabled,_tmpAuthToken,_tmpTokenExpiry);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getCurrentUserSync(final Continuation<? super User> $completion) {
    final String _sql = "SELECT `users`.`id` AS `id`, `users`.`name` AS `name`, `users`.`email` AS `email`, `users`.`phoneNumber` AS `phoneNumber`, `users`.`accountNumber` AS `accountNumber`, `users`.`bankCode` AS `bankCode`, `users`.`useBiometricAuth` AS `useBiometricAuth`, `users`.`enableNotifications` AS `enableNotifications`, `users`.`darkModeEnabled` AS `darkModeEnabled`, `users`.`authToken` AS `authToken`, `users`.`tokenExpiry` AS `tokenExpiry` FROM users LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<User>() {
      @Override
      @Nullable
      public User call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfName = 1;
          final int _cursorIndexOfEmail = 2;
          final int _cursorIndexOfPhoneNumber = 3;
          final int _cursorIndexOfAccountNumber = 4;
          final int _cursorIndexOfBankCode = 5;
          final int _cursorIndexOfUseBiometricAuth = 6;
          final int _cursorIndexOfEnableNotifications = 7;
          final int _cursorIndexOfDarkModeEnabled = 8;
          final int _cursorIndexOfAuthToken = 9;
          final int _cursorIndexOfTokenExpiry = 10;
          final User _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPhoneNumber;
            if (_cursor.isNull(_cursorIndexOfPhoneNumber)) {
              _tmpPhoneNumber = null;
            } else {
              _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
            }
            final String _tmpAccountNumber;
            if (_cursor.isNull(_cursorIndexOfAccountNumber)) {
              _tmpAccountNumber = null;
            } else {
              _tmpAccountNumber = _cursor.getString(_cursorIndexOfAccountNumber);
            }
            final String _tmpBankCode;
            if (_cursor.isNull(_cursorIndexOfBankCode)) {
              _tmpBankCode = null;
            } else {
              _tmpBankCode = _cursor.getString(_cursorIndexOfBankCode);
            }
            final boolean _tmpUseBiometricAuth;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfUseBiometricAuth);
            _tmpUseBiometricAuth = _tmp != 0;
            final boolean _tmpEnableNotifications;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfEnableNotifications);
            _tmpEnableNotifications = _tmp_1 != 0;
            final boolean _tmpDarkModeEnabled;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfDarkModeEnabled);
            _tmpDarkModeEnabled = _tmp_2 != 0;
            final String _tmpAuthToken;
            if (_cursor.isNull(_cursorIndexOfAuthToken)) {
              _tmpAuthToken = null;
            } else {
              _tmpAuthToken = _cursor.getString(_cursorIndexOfAuthToken);
            }
            final Long _tmpTokenExpiry;
            if (_cursor.isNull(_cursorIndexOfTokenExpiry)) {
              _tmpTokenExpiry = null;
            } else {
              _tmpTokenExpiry = _cursor.getLong(_cursorIndexOfTokenExpiry);
            }
            _result = new User(_tmpId,_tmpName,_tmpEmail,_tmpPhoneNumber,_tmpAccountNumber,_tmpBankCode,_tmpUseBiometricAuth,_tmpEnableNotifications,_tmpDarkModeEnabled,_tmpAuthToken,_tmpTokenExpiry);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

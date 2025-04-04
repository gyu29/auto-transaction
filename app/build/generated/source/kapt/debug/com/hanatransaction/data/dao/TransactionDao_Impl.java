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
import com.hanatransaction.data.model.RepeatInterval;
import com.hanatransaction.data.model.Transaction;
import com.hanatransaction.data.util.DateConverter;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Transaction> __insertionAdapterOfTransaction;

  private final DateConverter __dateConverter = new DateConverter();

  private final EntityDeletionOrUpdateAdapter<Transaction> __deletionAdapterOfTransaction;

  private final EntityDeletionOrUpdateAdapter<Transaction> __updateAdapterOfTransaction;

  private final SharedSQLiteStatement __preparedStmtOfMarkAsCompleted;

  private final SharedSQLiteStatement __preparedStmtOfUpdateNextExecutionDate;

  private final SharedSQLiteStatement __preparedStmtOfClearAllTransactions;

  private final SharedSQLiteStatement __preparedStmtOfClearCompletedTransactions;

  public TransactionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransaction = new EntityInsertionAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `transactions` (`id`,`amount`,`recipientName`,`accountNumber`,`memo`,`scheduledDate`,`repeatInterval`,`isCompleted`,`createdAt`,`updatedAt`,`lastExecutedAt`,`nextExecutionDate`,`transactionId`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindDouble(2, entity.getAmount());
        if (entity.getRecipientName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getRecipientName());
        }
        if (entity.getAccountNumber() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getAccountNumber());
        }
        if (entity.getMemo() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getMemo());
        }
        final Long _tmp = __dateConverter.dateToTimestamp(entity.getScheduledDate());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, _tmp);
        }
        statement.bindString(7, __RepeatInterval_enumToString(entity.getRepeatInterval()));
        final int _tmp_1 = entity.isCompleted() ? 1 : 0;
        statement.bindLong(8, _tmp_1);
        final Long _tmp_2 = __dateConverter.dateToTimestamp(entity.getCreatedAt());
        if (_tmp_2 == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, _tmp_2);
        }
        final Long _tmp_3 = __dateConverter.dateToTimestamp(entity.getUpdatedAt());
        if (_tmp_3 == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, _tmp_3);
        }
        final Long _tmp_4 = __dateConverter.dateToTimestamp(entity.getLastExecutedAt());
        if (_tmp_4 == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, _tmp_4);
        }
        final Long _tmp_5 = __dateConverter.dateToTimestamp(entity.getNextExecutionDate());
        if (_tmp_5 == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, _tmp_5);
        }
        if (entity.getTransactionId() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getTransactionId());
        }
      }
    };
    this.__deletionAdapterOfTransaction = new EntityDeletionOrUpdateAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `transactions` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfTransaction = new EntityDeletionOrUpdateAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `transactions` SET `id` = ?,`amount` = ?,`recipientName` = ?,`accountNumber` = ?,`memo` = ?,`scheduledDate` = ?,`repeatInterval` = ?,`isCompleted` = ?,`createdAt` = ?,`updatedAt` = ?,`lastExecutedAt` = ?,`nextExecutionDate` = ?,`transactionId` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindDouble(2, entity.getAmount());
        if (entity.getRecipientName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getRecipientName());
        }
        if (entity.getAccountNumber() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getAccountNumber());
        }
        if (entity.getMemo() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getMemo());
        }
        final Long _tmp = __dateConverter.dateToTimestamp(entity.getScheduledDate());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, _tmp);
        }
        statement.bindString(7, __RepeatInterval_enumToString(entity.getRepeatInterval()));
        final int _tmp_1 = entity.isCompleted() ? 1 : 0;
        statement.bindLong(8, _tmp_1);
        final Long _tmp_2 = __dateConverter.dateToTimestamp(entity.getCreatedAt());
        if (_tmp_2 == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, _tmp_2);
        }
        final Long _tmp_3 = __dateConverter.dateToTimestamp(entity.getUpdatedAt());
        if (_tmp_3 == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, _tmp_3);
        }
        final Long _tmp_4 = __dateConverter.dateToTimestamp(entity.getLastExecutedAt());
        if (_tmp_4 == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, _tmp_4);
        }
        final Long _tmp_5 = __dateConverter.dateToTimestamp(entity.getNextExecutionDate());
        if (_tmp_5 == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, _tmp_5);
        }
        if (entity.getTransactionId() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getTransactionId());
        }
        statement.bindLong(14, entity.getId());
      }
    };
    this.__preparedStmtOfMarkAsCompleted = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE transactions SET isCompleted = 1, lastExecutedAt = ?, transactionId = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateNextExecutionDate = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE transactions SET nextExecutionDate = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearAllTransactions = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM transactions";
        return _query;
      }
    };
    this.__preparedStmtOfClearCompletedTransactions = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM transactions WHERE isCompleted = 1";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final Transaction transaction,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfTransaction.insertAndReturnId(transaction);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final Transaction transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTransaction.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Transaction transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTransaction.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object markAsCompleted(final long id, final Date executionDate, final String transactionId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAsCompleted.acquire();
        int _argIndex = 1;
        final Long _tmp = __dateConverter.dateToTimestamp(executionDate);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, _tmp);
        }
        _argIndex = 2;
        if (transactionId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, transactionId);
        }
        _argIndex = 3;
        _stmt.bindLong(_argIndex, id);
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
          __preparedStmtOfMarkAsCompleted.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateNextExecutionDate(final long id, final Date nextDate,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateNextExecutionDate.acquire();
        int _argIndex = 1;
        final Long _tmp = __dateConverter.dateToTimestamp(nextDate);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, _tmp);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, id);
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
          __preparedStmtOfUpdateNextExecutionDate.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAllTransactions(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAllTransactions.acquire();
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
          __preparedStmtOfClearAllTransactions.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearCompletedTransactions(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearCompletedTransactions.acquire();
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
          __preparedStmtOfClearCompletedTransactions.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Transaction>> getAllTransactions() {
    final String _sql = "SELECT `transactions`.`id` AS `id`, `transactions`.`amount` AS `amount`, `transactions`.`recipientName` AS `recipientName`, `transactions`.`accountNumber` AS `accountNumber`, `transactions`.`memo` AS `memo`, `transactions`.`scheduledDate` AS `scheduledDate`, `transactions`.`repeatInterval` AS `repeatInterval`, `transactions`.`isCompleted` AS `isCompleted`, `transactions`.`createdAt` AS `createdAt`, `transactions`.`updatedAt` AS `updatedAt`, `transactions`.`lastExecutedAt` AS `lastExecutedAt`, `transactions`.`nextExecutionDate` AS `nextExecutionDate`, `transactions`.`transactionId` AS `transactionId` FROM transactions ORDER BY scheduledDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions"}, false, new Callable<List<Transaction>>() {
      @Override
      @Nullable
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfAmount = 1;
          final int _cursorIndexOfRecipientName = 2;
          final int _cursorIndexOfAccountNumber = 3;
          final int _cursorIndexOfMemo = 4;
          final int _cursorIndexOfScheduledDate = 5;
          final int _cursorIndexOfRepeatInterval = 6;
          final int _cursorIndexOfIsCompleted = 7;
          final int _cursorIndexOfCreatedAt = 8;
          final int _cursorIndexOfUpdatedAt = 9;
          final int _cursorIndexOfLastExecutedAt = 10;
          final int _cursorIndexOfNextExecutionDate = 11;
          final int _cursorIndexOfTransactionId = 12;
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpRecipientName;
            if (_cursor.isNull(_cursorIndexOfRecipientName)) {
              _tmpRecipientName = null;
            } else {
              _tmpRecipientName = _cursor.getString(_cursorIndexOfRecipientName);
            }
            final String _tmpAccountNumber;
            if (_cursor.isNull(_cursorIndexOfAccountNumber)) {
              _tmpAccountNumber = null;
            } else {
              _tmpAccountNumber = _cursor.getString(_cursorIndexOfAccountNumber);
            }
            final String _tmpMemo;
            if (_cursor.isNull(_cursorIndexOfMemo)) {
              _tmpMemo = null;
            } else {
              _tmpMemo = _cursor.getString(_cursorIndexOfMemo);
            }
            final Date _tmpScheduledDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfScheduledDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfScheduledDate);
            }
            _tmpScheduledDate = __dateConverter.fromTimestamp(_tmp);
            final RepeatInterval _tmpRepeatInterval;
            _tmpRepeatInterval = __RepeatInterval_stringToEnum(_cursor.getString(_cursorIndexOfRepeatInterval));
            final boolean _tmpIsCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_1 != 0;
            final Date _tmpCreatedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            _tmpCreatedAt = __dateConverter.fromTimestamp(_tmp_2);
            final Date _tmpUpdatedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            _tmpUpdatedAt = __dateConverter.fromTimestamp(_tmp_3);
            final Date _tmpLastExecutedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfLastExecutedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfLastExecutedAt);
            }
            _tmpLastExecutedAt = __dateConverter.fromTimestamp(_tmp_4);
            final Date _tmpNextExecutionDate;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfNextExecutionDate)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfNextExecutionDate);
            }
            _tmpNextExecutionDate = __dateConverter.fromTimestamp(_tmp_5);
            final String _tmpTransactionId;
            if (_cursor.isNull(_cursorIndexOfTransactionId)) {
              _tmpTransactionId = null;
            } else {
              _tmpTransactionId = _cursor.getString(_cursorIndexOfTransactionId);
            }
            _item = new Transaction(_tmpId,_tmpAmount,_tmpRecipientName,_tmpAccountNumber,_tmpMemo,_tmpScheduledDate,_tmpRepeatInterval,_tmpIsCompleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpLastExecutedAt,_tmpNextExecutionDate,_tmpTransactionId);
            _result.add(_item);
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
  public Object getTransactionById(final long id,
      final Continuation<? super Transaction> $completion) {
    final String _sql = "SELECT * FROM transactions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Transaction>() {
      @Override
      @Nullable
      public Transaction call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfRecipientName = CursorUtil.getColumnIndexOrThrow(_cursor, "recipientName");
          final int _cursorIndexOfAccountNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "accountNumber");
          final int _cursorIndexOfMemo = CursorUtil.getColumnIndexOrThrow(_cursor, "memo");
          final int _cursorIndexOfScheduledDate = CursorUtil.getColumnIndexOrThrow(_cursor, "scheduledDate");
          final int _cursorIndexOfRepeatInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatInterval");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfLastExecutedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastExecutedAt");
          final int _cursorIndexOfNextExecutionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextExecutionDate");
          final int _cursorIndexOfTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionId");
          final Transaction _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpRecipientName;
            if (_cursor.isNull(_cursorIndexOfRecipientName)) {
              _tmpRecipientName = null;
            } else {
              _tmpRecipientName = _cursor.getString(_cursorIndexOfRecipientName);
            }
            final String _tmpAccountNumber;
            if (_cursor.isNull(_cursorIndexOfAccountNumber)) {
              _tmpAccountNumber = null;
            } else {
              _tmpAccountNumber = _cursor.getString(_cursorIndexOfAccountNumber);
            }
            final String _tmpMemo;
            if (_cursor.isNull(_cursorIndexOfMemo)) {
              _tmpMemo = null;
            } else {
              _tmpMemo = _cursor.getString(_cursorIndexOfMemo);
            }
            final Date _tmpScheduledDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfScheduledDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfScheduledDate);
            }
            _tmpScheduledDate = __dateConverter.fromTimestamp(_tmp);
            final RepeatInterval _tmpRepeatInterval;
            _tmpRepeatInterval = __RepeatInterval_stringToEnum(_cursor.getString(_cursorIndexOfRepeatInterval));
            final boolean _tmpIsCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_1 != 0;
            final Date _tmpCreatedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            _tmpCreatedAt = __dateConverter.fromTimestamp(_tmp_2);
            final Date _tmpUpdatedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            _tmpUpdatedAt = __dateConverter.fromTimestamp(_tmp_3);
            final Date _tmpLastExecutedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfLastExecutedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfLastExecutedAt);
            }
            _tmpLastExecutedAt = __dateConverter.fromTimestamp(_tmp_4);
            final Date _tmpNextExecutionDate;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfNextExecutionDate)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfNextExecutionDate);
            }
            _tmpNextExecutionDate = __dateConverter.fromTimestamp(_tmp_5);
            final String _tmpTransactionId;
            if (_cursor.isNull(_cursorIndexOfTransactionId)) {
              _tmpTransactionId = null;
            } else {
              _tmpTransactionId = _cursor.getString(_cursorIndexOfTransactionId);
            }
            _result = new Transaction(_tmpId,_tmpAmount,_tmpRecipientName,_tmpAccountNumber,_tmpMemo,_tmpScheduledDate,_tmpRepeatInterval,_tmpIsCompleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpLastExecutedAt,_tmpNextExecutionDate,_tmpTransactionId);
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
  public LiveData<List<Transaction>> getPendingTransactions() {
    final String _sql = "SELECT `transactions`.`id` AS `id`, `transactions`.`amount` AS `amount`, `transactions`.`recipientName` AS `recipientName`, `transactions`.`accountNumber` AS `accountNumber`, `transactions`.`memo` AS `memo`, `transactions`.`scheduledDate` AS `scheduledDate`, `transactions`.`repeatInterval` AS `repeatInterval`, `transactions`.`isCompleted` AS `isCompleted`, `transactions`.`createdAt` AS `createdAt`, `transactions`.`updatedAt` AS `updatedAt`, `transactions`.`lastExecutedAt` AS `lastExecutedAt`, `transactions`.`nextExecutionDate` AS `nextExecutionDate`, `transactions`.`transactionId` AS `transactionId` FROM transactions WHERE isCompleted = 0 ORDER BY scheduledDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions"}, false, new Callable<List<Transaction>>() {
      @Override
      @Nullable
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfAmount = 1;
          final int _cursorIndexOfRecipientName = 2;
          final int _cursorIndexOfAccountNumber = 3;
          final int _cursorIndexOfMemo = 4;
          final int _cursorIndexOfScheduledDate = 5;
          final int _cursorIndexOfRepeatInterval = 6;
          final int _cursorIndexOfIsCompleted = 7;
          final int _cursorIndexOfCreatedAt = 8;
          final int _cursorIndexOfUpdatedAt = 9;
          final int _cursorIndexOfLastExecutedAt = 10;
          final int _cursorIndexOfNextExecutionDate = 11;
          final int _cursorIndexOfTransactionId = 12;
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpRecipientName;
            if (_cursor.isNull(_cursorIndexOfRecipientName)) {
              _tmpRecipientName = null;
            } else {
              _tmpRecipientName = _cursor.getString(_cursorIndexOfRecipientName);
            }
            final String _tmpAccountNumber;
            if (_cursor.isNull(_cursorIndexOfAccountNumber)) {
              _tmpAccountNumber = null;
            } else {
              _tmpAccountNumber = _cursor.getString(_cursorIndexOfAccountNumber);
            }
            final String _tmpMemo;
            if (_cursor.isNull(_cursorIndexOfMemo)) {
              _tmpMemo = null;
            } else {
              _tmpMemo = _cursor.getString(_cursorIndexOfMemo);
            }
            final Date _tmpScheduledDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfScheduledDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfScheduledDate);
            }
            _tmpScheduledDate = __dateConverter.fromTimestamp(_tmp);
            final RepeatInterval _tmpRepeatInterval;
            _tmpRepeatInterval = __RepeatInterval_stringToEnum(_cursor.getString(_cursorIndexOfRepeatInterval));
            final boolean _tmpIsCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_1 != 0;
            final Date _tmpCreatedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            _tmpCreatedAt = __dateConverter.fromTimestamp(_tmp_2);
            final Date _tmpUpdatedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            _tmpUpdatedAt = __dateConverter.fromTimestamp(_tmp_3);
            final Date _tmpLastExecutedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfLastExecutedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfLastExecutedAt);
            }
            _tmpLastExecutedAt = __dateConverter.fromTimestamp(_tmp_4);
            final Date _tmpNextExecutionDate;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfNextExecutionDate)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfNextExecutionDate);
            }
            _tmpNextExecutionDate = __dateConverter.fromTimestamp(_tmp_5);
            final String _tmpTransactionId;
            if (_cursor.isNull(_cursorIndexOfTransactionId)) {
              _tmpTransactionId = null;
            } else {
              _tmpTransactionId = _cursor.getString(_cursorIndexOfTransactionId);
            }
            _item = new Transaction(_tmpId,_tmpAmount,_tmpRecipientName,_tmpAccountNumber,_tmpMemo,_tmpScheduledDate,_tmpRepeatInterval,_tmpIsCompleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpLastExecutedAt,_tmpNextExecutionDate,_tmpTransactionId);
            _result.add(_item);
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
  public Object getTransactionsDueBy(final Date date,
      final Continuation<? super List<Transaction>> $completion) {
    final String _sql = "SELECT * FROM transactions WHERE scheduledDate <= ? AND isCompleted = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final Long _tmp = __dateConverter.dateToTimestamp(date);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Transaction>>() {
      @Override
      @NonNull
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfRecipientName = CursorUtil.getColumnIndexOrThrow(_cursor, "recipientName");
          final int _cursorIndexOfAccountNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "accountNumber");
          final int _cursorIndexOfMemo = CursorUtil.getColumnIndexOrThrow(_cursor, "memo");
          final int _cursorIndexOfScheduledDate = CursorUtil.getColumnIndexOrThrow(_cursor, "scheduledDate");
          final int _cursorIndexOfRepeatInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "repeatInterval");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfLastExecutedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastExecutedAt");
          final int _cursorIndexOfNextExecutionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextExecutionDate");
          final int _cursorIndexOfTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionId");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpRecipientName;
            if (_cursor.isNull(_cursorIndexOfRecipientName)) {
              _tmpRecipientName = null;
            } else {
              _tmpRecipientName = _cursor.getString(_cursorIndexOfRecipientName);
            }
            final String _tmpAccountNumber;
            if (_cursor.isNull(_cursorIndexOfAccountNumber)) {
              _tmpAccountNumber = null;
            } else {
              _tmpAccountNumber = _cursor.getString(_cursorIndexOfAccountNumber);
            }
            final String _tmpMemo;
            if (_cursor.isNull(_cursorIndexOfMemo)) {
              _tmpMemo = null;
            } else {
              _tmpMemo = _cursor.getString(_cursorIndexOfMemo);
            }
            final Date _tmpScheduledDate;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfScheduledDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfScheduledDate);
            }
            _tmpScheduledDate = __dateConverter.fromTimestamp(_tmp_1);
            final RepeatInterval _tmpRepeatInterval;
            _tmpRepeatInterval = __RepeatInterval_stringToEnum(_cursor.getString(_cursorIndexOfRepeatInterval));
            final boolean _tmpIsCompleted;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_2 != 0;
            final Date _tmpCreatedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            _tmpCreatedAt = __dateConverter.fromTimestamp(_tmp_3);
            final Date _tmpUpdatedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            _tmpUpdatedAt = __dateConverter.fromTimestamp(_tmp_4);
            final Date _tmpLastExecutedAt;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfLastExecutedAt)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfLastExecutedAt);
            }
            _tmpLastExecutedAt = __dateConverter.fromTimestamp(_tmp_5);
            final Date _tmpNextExecutionDate;
            final Long _tmp_6;
            if (_cursor.isNull(_cursorIndexOfNextExecutionDate)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getLong(_cursorIndexOfNextExecutionDate);
            }
            _tmpNextExecutionDate = __dateConverter.fromTimestamp(_tmp_6);
            final String _tmpTransactionId;
            if (_cursor.isNull(_cursorIndexOfTransactionId)) {
              _tmpTransactionId = null;
            } else {
              _tmpTransactionId = _cursor.getString(_cursorIndexOfTransactionId);
            }
            _item = new Transaction(_tmpId,_tmpAmount,_tmpRecipientName,_tmpAccountNumber,_tmpMemo,_tmpScheduledDate,_tmpRepeatInterval,_tmpIsCompleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpLastExecutedAt,_tmpNextExecutionDate,_tmpTransactionId);
            _result.add(_item);
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
  public LiveData<List<Transaction>> getRecurringTransactions() {
    final String _sql = "SELECT `transactions`.`id` AS `id`, `transactions`.`amount` AS `amount`, `transactions`.`recipientName` AS `recipientName`, `transactions`.`accountNumber` AS `accountNumber`, `transactions`.`memo` AS `memo`, `transactions`.`scheduledDate` AS `scheduledDate`, `transactions`.`repeatInterval` AS `repeatInterval`, `transactions`.`isCompleted` AS `isCompleted`, `transactions`.`createdAt` AS `createdAt`, `transactions`.`updatedAt` AS `updatedAt`, `transactions`.`lastExecutedAt` AS `lastExecutedAt`, `transactions`.`nextExecutionDate` AS `nextExecutionDate`, `transactions`.`transactionId` AS `transactionId` FROM transactions WHERE repeatInterval != 'ONCE' AND isCompleted = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions"}, false, new Callable<List<Transaction>>() {
      @Override
      @Nullable
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfAmount = 1;
          final int _cursorIndexOfRecipientName = 2;
          final int _cursorIndexOfAccountNumber = 3;
          final int _cursorIndexOfMemo = 4;
          final int _cursorIndexOfScheduledDate = 5;
          final int _cursorIndexOfRepeatInterval = 6;
          final int _cursorIndexOfIsCompleted = 7;
          final int _cursorIndexOfCreatedAt = 8;
          final int _cursorIndexOfUpdatedAt = 9;
          final int _cursorIndexOfLastExecutedAt = 10;
          final int _cursorIndexOfNextExecutionDate = 11;
          final int _cursorIndexOfTransactionId = 12;
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpRecipientName;
            if (_cursor.isNull(_cursorIndexOfRecipientName)) {
              _tmpRecipientName = null;
            } else {
              _tmpRecipientName = _cursor.getString(_cursorIndexOfRecipientName);
            }
            final String _tmpAccountNumber;
            if (_cursor.isNull(_cursorIndexOfAccountNumber)) {
              _tmpAccountNumber = null;
            } else {
              _tmpAccountNumber = _cursor.getString(_cursorIndexOfAccountNumber);
            }
            final String _tmpMemo;
            if (_cursor.isNull(_cursorIndexOfMemo)) {
              _tmpMemo = null;
            } else {
              _tmpMemo = _cursor.getString(_cursorIndexOfMemo);
            }
            final Date _tmpScheduledDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfScheduledDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfScheduledDate);
            }
            _tmpScheduledDate = __dateConverter.fromTimestamp(_tmp);
            final RepeatInterval _tmpRepeatInterval;
            _tmpRepeatInterval = __RepeatInterval_stringToEnum(_cursor.getString(_cursorIndexOfRepeatInterval));
            final boolean _tmpIsCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_1 != 0;
            final Date _tmpCreatedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            _tmpCreatedAt = __dateConverter.fromTimestamp(_tmp_2);
            final Date _tmpUpdatedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            _tmpUpdatedAt = __dateConverter.fromTimestamp(_tmp_3);
            final Date _tmpLastExecutedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfLastExecutedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfLastExecutedAt);
            }
            _tmpLastExecutedAt = __dateConverter.fromTimestamp(_tmp_4);
            final Date _tmpNextExecutionDate;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfNextExecutionDate)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfNextExecutionDate);
            }
            _tmpNextExecutionDate = __dateConverter.fromTimestamp(_tmp_5);
            final String _tmpTransactionId;
            if (_cursor.isNull(_cursorIndexOfTransactionId)) {
              _tmpTransactionId = null;
            } else {
              _tmpTransactionId = _cursor.getString(_cursorIndexOfTransactionId);
            }
            _item = new Transaction(_tmpId,_tmpAmount,_tmpRecipientName,_tmpAccountNumber,_tmpMemo,_tmpScheduledDate,_tmpRepeatInterval,_tmpIsCompleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpLastExecutedAt,_tmpNextExecutionDate,_tmpTransactionId);
            _result.add(_item);
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
  public LiveData<List<Transaction>> getRecentCompletedTransactions() {
    final String _sql = "SELECT `transactions`.`id` AS `id`, `transactions`.`amount` AS `amount`, `transactions`.`recipientName` AS `recipientName`, `transactions`.`accountNumber` AS `accountNumber`, `transactions`.`memo` AS `memo`, `transactions`.`scheduledDate` AS `scheduledDate`, `transactions`.`repeatInterval` AS `repeatInterval`, `transactions`.`isCompleted` AS `isCompleted`, `transactions`.`createdAt` AS `createdAt`, `transactions`.`updatedAt` AS `updatedAt`, `transactions`.`lastExecutedAt` AS `lastExecutedAt`, `transactions`.`nextExecutionDate` AS `nextExecutionDate`, `transactions`.`transactionId` AS `transactionId` FROM transactions WHERE isCompleted = 1 ORDER BY lastExecutedAt DESC LIMIT 50";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions"}, false, new Callable<List<Transaction>>() {
      @Override
      @Nullable
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfAmount = 1;
          final int _cursorIndexOfRecipientName = 2;
          final int _cursorIndexOfAccountNumber = 3;
          final int _cursorIndexOfMemo = 4;
          final int _cursorIndexOfScheduledDate = 5;
          final int _cursorIndexOfRepeatInterval = 6;
          final int _cursorIndexOfIsCompleted = 7;
          final int _cursorIndexOfCreatedAt = 8;
          final int _cursorIndexOfUpdatedAt = 9;
          final int _cursorIndexOfLastExecutedAt = 10;
          final int _cursorIndexOfNextExecutionDate = 11;
          final int _cursorIndexOfTransactionId = 12;
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpRecipientName;
            if (_cursor.isNull(_cursorIndexOfRecipientName)) {
              _tmpRecipientName = null;
            } else {
              _tmpRecipientName = _cursor.getString(_cursorIndexOfRecipientName);
            }
            final String _tmpAccountNumber;
            if (_cursor.isNull(_cursorIndexOfAccountNumber)) {
              _tmpAccountNumber = null;
            } else {
              _tmpAccountNumber = _cursor.getString(_cursorIndexOfAccountNumber);
            }
            final String _tmpMemo;
            if (_cursor.isNull(_cursorIndexOfMemo)) {
              _tmpMemo = null;
            } else {
              _tmpMemo = _cursor.getString(_cursorIndexOfMemo);
            }
            final Date _tmpScheduledDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfScheduledDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfScheduledDate);
            }
            _tmpScheduledDate = __dateConverter.fromTimestamp(_tmp);
            final RepeatInterval _tmpRepeatInterval;
            _tmpRepeatInterval = __RepeatInterval_stringToEnum(_cursor.getString(_cursorIndexOfRepeatInterval));
            final boolean _tmpIsCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_1 != 0;
            final Date _tmpCreatedAt;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            _tmpCreatedAt = __dateConverter.fromTimestamp(_tmp_2);
            final Date _tmpUpdatedAt;
            final Long _tmp_3;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getLong(_cursorIndexOfUpdatedAt);
            }
            _tmpUpdatedAt = __dateConverter.fromTimestamp(_tmp_3);
            final Date _tmpLastExecutedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfLastExecutedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfLastExecutedAt);
            }
            _tmpLastExecutedAt = __dateConverter.fromTimestamp(_tmp_4);
            final Date _tmpNextExecutionDate;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfNextExecutionDate)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfNextExecutionDate);
            }
            _tmpNextExecutionDate = __dateConverter.fromTimestamp(_tmp_5);
            final String _tmpTransactionId;
            if (_cursor.isNull(_cursorIndexOfTransactionId)) {
              _tmpTransactionId = null;
            } else {
              _tmpTransactionId = _cursor.getString(_cursorIndexOfTransactionId);
            }
            _item = new Transaction(_tmpId,_tmpAmount,_tmpRecipientName,_tmpAccountNumber,_tmpMemo,_tmpScheduledDate,_tmpRepeatInterval,_tmpIsCompleted,_tmpCreatedAt,_tmpUpdatedAt,_tmpLastExecutedAt,_tmpNextExecutionDate,_tmpTransactionId);
            _result.add(_item);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __RepeatInterval_enumToString(@NonNull final RepeatInterval _value) {
    switch (_value) {
      case ONCE: return "ONCE";
      case DAILY: return "DAILY";
      case WEEKLY: return "WEEKLY";
      case MONTHLY: return "MONTHLY";
      case YEARLY: return "YEARLY";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private RepeatInterval __RepeatInterval_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "ONCE": return RepeatInterval.ONCE;
      case "DAILY": return RepeatInterval.DAILY;
      case "WEEKLY": return RepeatInterval.WEEKLY;
      case "MONTHLY": return RepeatInterval.MONTHLY;
      case "YEARLY": return RepeatInterval.YEARLY;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}

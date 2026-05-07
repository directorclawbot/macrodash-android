package com.macrodash.app.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.macrodash.app.data.local.entity.IndicatorEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class IndicatorDao_Impl implements IndicatorDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<IndicatorEntity> __insertionAdapterOfIndicatorEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public IndicatorDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfIndicatorEntity = new EntityInsertionAdapter<IndicatorEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `indicators` (`id`,`name`,`value`,`rawValue`,`percentChange`,`category`,`horizons`,`lastUpdated`,`fetchedAt`,`source`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final IndicatorEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getValue());
        statement.bindDouble(4, entity.getRawValue());
        statement.bindDouble(5, entity.getPercentChange());
        statement.bindString(6, entity.getCategory());
        statement.bindString(7, entity.getHorizons());
        statement.bindLong(8, entity.getLastUpdated());
        statement.bindLong(9, entity.getFetchedAt());
        statement.bindString(10, entity.getSource());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM indicators";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<IndicatorEntity> indicators,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfIndicatorEntity.insert(indicators);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insert(final IndicatorEntity indicator,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfIndicatorEntity.insert(indicator);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
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
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<IndicatorEntity>> getAllIndicators() {
    final String _sql = "SELECT * FROM indicators ORDER BY category, name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"indicators"}, new Callable<List<IndicatorEntity>>() {
      @Override
      @NonNull
      public List<IndicatorEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
          final int _cursorIndexOfRawValue = CursorUtil.getColumnIndexOrThrow(_cursor, "rawValue");
          final int _cursorIndexOfPercentChange = CursorUtil.getColumnIndexOrThrow(_cursor, "percentChange");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfHorizons = CursorUtil.getColumnIndexOrThrow(_cursor, "horizons");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfFetchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "fetchedAt");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final List<IndicatorEntity> _result = new ArrayList<IndicatorEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final IndicatorEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpValue;
            _tmpValue = _cursor.getString(_cursorIndexOfValue);
            final double _tmpRawValue;
            _tmpRawValue = _cursor.getDouble(_cursorIndexOfRawValue);
            final double _tmpPercentChange;
            _tmpPercentChange = _cursor.getDouble(_cursorIndexOfPercentChange);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpHorizons;
            _tmpHorizons = _cursor.getString(_cursorIndexOfHorizons);
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            final long _tmpFetchedAt;
            _tmpFetchedAt = _cursor.getLong(_cursorIndexOfFetchedAt);
            final String _tmpSource;
            _tmpSource = _cursor.getString(_cursorIndexOfSource);
            _item = new IndicatorEntity(_tmpId,_tmpName,_tmpValue,_tmpRawValue,_tmpPercentChange,_tmpCategory,_tmpHorizons,_tmpLastUpdated,_tmpFetchedAt,_tmpSource);
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
  public Flow<List<IndicatorEntity>> getIndicatorsByCategory(final String category) {
    final String _sql = "SELECT * FROM indicators WHERE category = ? ORDER BY name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, category);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"indicators"}, new Callable<List<IndicatorEntity>>() {
      @Override
      @NonNull
      public List<IndicatorEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
          final int _cursorIndexOfRawValue = CursorUtil.getColumnIndexOrThrow(_cursor, "rawValue");
          final int _cursorIndexOfPercentChange = CursorUtil.getColumnIndexOrThrow(_cursor, "percentChange");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfHorizons = CursorUtil.getColumnIndexOrThrow(_cursor, "horizons");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfFetchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "fetchedAt");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final List<IndicatorEntity> _result = new ArrayList<IndicatorEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final IndicatorEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpValue;
            _tmpValue = _cursor.getString(_cursorIndexOfValue);
            final double _tmpRawValue;
            _tmpRawValue = _cursor.getDouble(_cursorIndexOfRawValue);
            final double _tmpPercentChange;
            _tmpPercentChange = _cursor.getDouble(_cursorIndexOfPercentChange);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpHorizons;
            _tmpHorizons = _cursor.getString(_cursorIndexOfHorizons);
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            final long _tmpFetchedAt;
            _tmpFetchedAt = _cursor.getLong(_cursorIndexOfFetchedAt);
            final String _tmpSource;
            _tmpSource = _cursor.getString(_cursorIndexOfSource);
            _item = new IndicatorEntity(_tmpId,_tmpName,_tmpValue,_tmpRawValue,_tmpPercentChange,_tmpCategory,_tmpHorizons,_tmpLastUpdated,_tmpFetchedAt,_tmpSource);
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
}

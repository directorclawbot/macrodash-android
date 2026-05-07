package com.macrodash.app.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.macrodash.app.data.local.dao.AIAnalysisCacheDao;
import com.macrodash.app.data.local.dao.AIAnalysisCacheDao_Impl;
import com.macrodash.app.data.local.dao.IndicatorDao;
import com.macrodash.app.data.local.dao.IndicatorDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MacroDashDatabase_Impl extends MacroDashDatabase {
  private volatile IndicatorDao _indicatorDao;

  private volatile AIAnalysisCacheDao _aIAnalysisCacheDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `indicators` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `value` TEXT NOT NULL, `rawValue` REAL NOT NULL, `percentChange` REAL NOT NULL, `category` TEXT NOT NULL, `horizons` TEXT NOT NULL, `lastUpdated` INTEGER NOT NULL, `fetchedAt` INTEGER NOT NULL, `source` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `ai_analysis_cache` (`id` INTEGER NOT NULL, `content` TEXT NOT NULL, `provider` TEXT NOT NULL, `model` TEXT NOT NULL, `generatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'fbdefc9bf15322d7e31a026189ee2335')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `indicators`");
        db.execSQL("DROP TABLE IF EXISTS `ai_analysis_cache`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsIndicators = new HashMap<String, TableInfo.Column>(10);
        _columnsIndicators.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndicators.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndicators.put("value", new TableInfo.Column("value", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndicators.put("rawValue", new TableInfo.Column("rawValue", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndicators.put("percentChange", new TableInfo.Column("percentChange", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndicators.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndicators.put("horizons", new TableInfo.Column("horizons", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndicators.put("lastUpdated", new TableInfo.Column("lastUpdated", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndicators.put("fetchedAt", new TableInfo.Column("fetchedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndicators.put("source", new TableInfo.Column("source", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysIndicators = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesIndicators = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoIndicators = new TableInfo("indicators", _columnsIndicators, _foreignKeysIndicators, _indicesIndicators);
        final TableInfo _existingIndicators = TableInfo.read(db, "indicators");
        if (!_infoIndicators.equals(_existingIndicators)) {
          return new RoomOpenHelper.ValidationResult(false, "indicators(com.macrodash.app.data.local.entity.IndicatorEntity).\n"
                  + " Expected:\n" + _infoIndicators + "\n"
                  + " Found:\n" + _existingIndicators);
        }
        final HashMap<String, TableInfo.Column> _columnsAiAnalysisCache = new HashMap<String, TableInfo.Column>(5);
        _columnsAiAnalysisCache.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiAnalysisCache.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiAnalysisCache.put("provider", new TableInfo.Column("provider", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiAnalysisCache.put("model", new TableInfo.Column("model", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAiAnalysisCache.put("generatedAt", new TableInfo.Column("generatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAiAnalysisCache = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAiAnalysisCache = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAiAnalysisCache = new TableInfo("ai_analysis_cache", _columnsAiAnalysisCache, _foreignKeysAiAnalysisCache, _indicesAiAnalysisCache);
        final TableInfo _existingAiAnalysisCache = TableInfo.read(db, "ai_analysis_cache");
        if (!_infoAiAnalysisCache.equals(_existingAiAnalysisCache)) {
          return new RoomOpenHelper.ValidationResult(false, "ai_analysis_cache(com.macrodash.app.data.local.entity.AIAnalysisCacheEntity).\n"
                  + " Expected:\n" + _infoAiAnalysisCache + "\n"
                  + " Found:\n" + _existingAiAnalysisCache);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "fbdefc9bf15322d7e31a026189ee2335", "ea9ff7ec3dabeae10f7390feb053080c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "indicators","ai_analysis_cache");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `indicators`");
      _db.execSQL("DELETE FROM `ai_analysis_cache`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(IndicatorDao.class, IndicatorDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AIAnalysisCacheDao.class, AIAnalysisCacheDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public IndicatorDao indicatorDao() {
    if (_indicatorDao != null) {
      return _indicatorDao;
    } else {
      synchronized(this) {
        if(_indicatorDao == null) {
          _indicatorDao = new IndicatorDao_Impl(this);
        }
        return _indicatorDao;
      }
    }
  }

  @Override
  public AIAnalysisCacheDao aiAnalysisCacheDao() {
    if (_aIAnalysisCacheDao != null) {
      return _aIAnalysisCacheDao;
    } else {
      synchronized(this) {
        if(_aIAnalysisCacheDao == null) {
          _aIAnalysisCacheDao = new AIAnalysisCacheDao_Impl(this);
        }
        return _aIAnalysisCacheDao;
      }
    }
  }
}

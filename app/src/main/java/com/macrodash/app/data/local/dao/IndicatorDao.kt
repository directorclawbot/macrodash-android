package com.macrodash.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.macrodash.app.data.local.entity.AIAnalysisCacheEntity
import com.macrodash.app.data.local.entity.IndicatorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IndicatorDao {
    @Query("SELECT * FROM indicators ORDER BY category, name")
    fun getAllIndicators(): Flow<List<IndicatorEntity>>

    @Query("SELECT * FROM indicators WHERE category = :category ORDER BY name")
    fun getIndicatorsByCategory(category: String): Flow<List<IndicatorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(indicators: List<IndicatorEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(indicator: IndicatorEntity)

    @Query("DELETE FROM indicators")
    suspend fun deleteAll()
}

@Dao
interface AIAnalysisCacheDao {
    @Query("SELECT * FROM ai_analysis_cache WHERE id = 1")
    suspend fun getCachedAnalysis(): AIAnalysisCacheEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(analysis: AIAnalysisCacheEntity)

    @Query("DELETE FROM ai_analysis_cache")
    suspend fun clearCache()
}
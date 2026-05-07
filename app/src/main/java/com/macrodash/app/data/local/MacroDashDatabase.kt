package com.macrodash.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.macrodash.app.data.local.dao.AIAnalysisCacheDao
import com.macrodash.app.data.local.dao.IndicatorDao
import com.macrodash.app.data.local.entity.AIAnalysisCacheEntity
import com.macrodash.app.data.local.entity.IndicatorEntity

@Database(
    entities = [IndicatorEntity::class, AIAnalysisCacheEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MacroDashDatabase : RoomDatabase() {
    abstract fun indicatorDao(): IndicatorDao
    abstract fun aiAnalysisCacheDao(): AIAnalysisCacheDao
}
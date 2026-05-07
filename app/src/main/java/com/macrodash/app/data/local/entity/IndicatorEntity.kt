package com.macrodash.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "indicators")
data class IndicatorEntity(
    @PrimaryKey val id: String,
    val name: String,
    val value: String,
    val rawValue: Double,
    val percentChange: Double,
    val category: String,
    val horizons: String,
    val lastUpdated: Long,
    val fetchedAt: Long,
    val source: String
)

@Entity(tableName = "ai_analysis_cache")
data class AIAnalysisCacheEntity(
    @PrimaryKey val id: Int = 1,
    val content: String,
    val provider: String,
    val model: String,
    val generatedAt: Long
)
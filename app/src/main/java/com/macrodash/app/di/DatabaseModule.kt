package com.macrodash.app.di

import android.content.Context
import androidx.room.Room
import com.macrodash.app.data.local.MacroDashDatabase
import com.macrodash.app.data.local.dao.IndicatorDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MacroDashDatabase {
        return Room.databaseBuilder(
            context,
            MacroDashDatabase::class.java,
            "macrodash.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideIndicatorDao(database: MacroDashDatabase): IndicatorDao {
        return database.indicatorDao()
    }
}
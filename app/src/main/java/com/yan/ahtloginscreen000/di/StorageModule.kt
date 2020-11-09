package com.yan.ahtloginscreen000.di

import android.content.Context
import androidx.room.Room
import com.yan.ahtloginscreen000.data.database.InfoDAO
import com.yan.ahtloginscreen000.data.database.InfoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun provideInfoDAO(db: InfoDatabase): InfoDAO = db.infoDao()

    /**
     * Provides app AppDatabase
     */
    @Singleton
    @Provides
    fun provideDb(context: Context): InfoDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            InfoDatabase::class.java, "InfoDatabase"
        ).fallbackToDestructiveMigration().build()

}
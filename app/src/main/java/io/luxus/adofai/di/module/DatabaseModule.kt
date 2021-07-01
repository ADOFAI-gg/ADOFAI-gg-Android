package io.luxus.adofai.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.luxus.adofai.data.source.local.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, "Database")
            .build()

    @Provides
    fun provideInitializeDao(appDatabase: AppDatabase) =
        appDatabase.initializeDao()


}
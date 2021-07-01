package io.luxus.adofai.di.module

import android.app.Application
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.luxus.adofai.data.source.local.AppDatabase
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
class DatabaseTestModule {

    @Singleton
    @Provides
    fun provideInMemoryDatabase(): AppDatabase {
        return Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context, AppDatabase::class.java
        ).build()
    }

    @Provides
    fun provideInitializeDao(appDatabase: AppDatabase) =
        appDatabase.initializeDao()

    @Provides
    fun provideLevelDao(appDatabase: AppDatabase) =
        appDatabase.levelDao()

}
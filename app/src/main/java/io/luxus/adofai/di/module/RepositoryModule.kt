package io.luxus.adofai.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.luxus.adofai.data.repository.LevelRepositoryImpl
import io.luxus.adofai.domain.repository.LevelRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLevelRepository(levelRepositoryImpl: LevelRepositoryImpl): LevelRepository

}
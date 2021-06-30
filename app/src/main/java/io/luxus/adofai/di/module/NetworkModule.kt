package io.luxus.adofai.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.luxus.adofai.data.source.remote.service.GoogleSheetService
import io.luxus.adofai.data.source.remote.service.factory.JsonTextConverterFactory
import io.luxus.adofai.util.Constants
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesGoogleSheetService(
        jsonTextConverterFactory: JsonTextConverterFactory
    ): GoogleSheetService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(jsonTextConverterFactory)
            .build()
            .create(GoogleSheetService::class.java)
    }

    @Provides
    fun providesJsonTextConverterFactory(): JsonTextConverterFactory {
        return JsonTextConverterFactory.create()
    }


}
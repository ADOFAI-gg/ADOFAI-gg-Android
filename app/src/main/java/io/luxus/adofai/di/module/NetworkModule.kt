package io.luxus.adofai.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.luxus.adofai.data.source.remote.service.factory.JsonTextConverterFactory
import io.luxus.adofai.data.source.remote.service.GoogleSheetService
import io.luxus.adofai.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesGoogleSheetService(
        jsonTextConverterFactory: JsonTextConverterFactory,
        gsonConverterFactory: GsonConverterFactory,
    ): GoogleSheetService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(jsonTextConverterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(GoogleSheetService::class.java)
    }

    @Provides
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun providesJsonTextConverterFactory(): JsonTextConverterFactory {
        return JsonTextConverterFactory.create()
    }

}
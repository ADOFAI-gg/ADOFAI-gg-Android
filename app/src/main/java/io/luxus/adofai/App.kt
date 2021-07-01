package io.luxus.adofai

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.luxus.adofai.domain.repository.InitializeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var initializeRepository: InitializeRepository

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            initializeRepository.initialize()
        }
    }

}
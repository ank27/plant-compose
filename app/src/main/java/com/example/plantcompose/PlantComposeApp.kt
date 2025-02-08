package com.example.plantcompose

import android.app.Application
import com.example.plantcompose.di.plantComposeModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Application class for PlantComposeApp.
 */
class PlantComposeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PlantComposeApp)
            modules(plantComposeModules)
        }
    }
}

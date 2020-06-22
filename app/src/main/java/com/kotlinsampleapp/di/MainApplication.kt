package com.kotlinsampleapp.di

import android.app.Application
import com.kotlinsampleapp.mainfeature.di.mainFeatureModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Creates a new Koin Application for DI
 *
 * @author federico.giardini
 */
@ExperimentalCoroutinesApi
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(
                viewModelsModule,
                networkModule,
                mainFeatureModule
            ))
        }
    }
}
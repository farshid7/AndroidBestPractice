package com.example.androidbestpractice

import android.app.Application
import com.example.androidbestpractice.di.AppModule.appModule
import com.example.androidbestpractice.di.RepositoryModule.repositoryModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(appModule)
            modules(repositoryModule)
        }
    }
}
package com.example.androidbestpractice.di

import androidx.room.Room
import com.example.androidbestpractice.di.databaseManager.AppDatabase
import com.example.androidbestpractice.di.networkManager.RetrofitBuilderImpl
import com.example.androidbestpractice.di.networkManager.RetrofitService
import org.koin.dsl.module

object AppModule {
    val appModule = module {
        single<RetrofitService> {
            RetrofitBuilderImpl().makeRetrofitService("https://api.themoviedb.org")
                .create(RetrofitService::class.java)
        }
        single<AppDatabase> {
            Room.databaseBuilder(
                get(),
                AppDatabase::class.java, "app_database"
            ).build()
        }

    }
}
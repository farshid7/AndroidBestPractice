package com.example.androidbestpractice.di

import com.example.androidbestpractice.di.networkManager.RetrofitBuilderImpl
import com.example.androidbestpractice.di.networkManager.RetrofitService
import org.koin.dsl.module

object AppModule {
    val appModule = module {
        single<RetrofitService> {
            RetrofitBuilderImpl().makeRetrofitService("https://api.themoviedb.org")
                .create(RetrofitService::class.java)
        }

    }
}
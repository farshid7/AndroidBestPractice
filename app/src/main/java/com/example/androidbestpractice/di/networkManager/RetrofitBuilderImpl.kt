package com.example.androidbestpractice.di.networkManager

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilderImpl {
    fun makeRetrofitService(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
   //         .client(HeaderInterceptor().build(accountManager))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }}
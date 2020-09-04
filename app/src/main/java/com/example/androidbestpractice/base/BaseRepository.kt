package com.example.androidbestpractice.base

import com.example.androidbestpractice.di.databaseManager.AppDatabase
import com.example.androidbestpractice.di.networkManager.ErrorBody
import com.example.androidbestpractice.di.networkManager.Resource
import com.example.androidbestpractice.di.networkManager.RetrofitService
import com.google.gson.Gson
import org.koin.java.KoinJavaComponent.inject
import retrofit2.HttpException

abstract class BaseRepository {
    protected val retrofitService by inject(RetrofitService::class.java)
    protected val appDatabase by inject(AppDatabase::class.java)

    protected fun <T> okHandler(data: T): Resource<T> {
        return Resource.success(data = data)
    }

    protected fun <T> exceptionHandler(exception: Exception): Resource<T> {
        return if (exception is HttpException) try {
            Resource.error(
                null,
                Gson().fromJson(
                    exception.response()!!.errorBody()!!.string(),
                    ErrorBody::class.java
                )
            )
        } catch (e: java.lang.Exception) {
            Resource.error(null, ErrorBody(arrayListOf()))
        } else {
            Resource.failed()
        }
    }
}
package com.example.androidbestpractice.view.ui.home

import androidx.lifecycle.liveData
import com.example.androidbestpractice.base.BaseRepository
import com.example.androidbestpractice.di.networkManager.Resource
import com.example.androidbestpractice.entity.DataResponse
import kotlinx.coroutines.Dispatchers

class HomeFragmentRepository : BaseRepository() {

    fun getDiscover() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            emit(okHandler(retrofitService.getDiscover()))
        } catch (exception: Exception) {
            emit(exceptionHandler<DataResponse>(exception))
        }
    }
}
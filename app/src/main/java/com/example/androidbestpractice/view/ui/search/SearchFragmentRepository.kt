package com.example.androidbestpractice.view.ui.search

import androidx.lifecycle.MutableLiveData
import com.example.androidbestpractice.base.BaseRepository
import com.example.androidbestpractice.di.networkManager.Resource
import com.example.androidbestpractice.entity.DataResponse

class SearchFragmentRepository : BaseRepository() {

    suspend fun search(query: String, liveData: MutableLiveData<Resource<DataResponse>>) {
        liveData.postValue(Resource.loading())
        try {
            liveData.postValue(okHandler(retrofitService.search(query)))
        } catch (exception: Exception) {
            liveData.postValue(exceptionHandler(exception))
        }
    }
}
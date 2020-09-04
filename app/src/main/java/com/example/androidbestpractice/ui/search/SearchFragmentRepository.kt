package com.example.androidbestpractice.ui.search

import androidx.lifecycle.MutableLiveData
import com.example.androidbestpractice.base.BaseRepository
import com.example.androidbestpractice.di.networkManager.Resource
import com.example.androidbestpractice.entity.DataResponse

class SearchFragmentRepository : BaseRepository() {

    suspend fun search(query: String, liveData: MutableLiveData<Resource<DataResponse>>) {
        liveData.value = Resource.loading()
        try {
            liveData.value = okHandler(retrofitService.getDiscover())
        } catch (exception: Exception) {
            liveData.value = exceptionHandler(exception)
        }
    }
}
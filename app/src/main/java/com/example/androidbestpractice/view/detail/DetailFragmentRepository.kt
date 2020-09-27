package com.example.androidbestpractice.view.detail

import androidx.lifecycle.liveData
import com.example.androidbestpractice.base.BaseRepository
import com.example.androidbestpractice.di.networkManager.Resource
import com.example.androidbestpractice.entity.DataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFragmentRepository : BaseRepository() {


    fun getExists(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            emit(Resource.success(appDatabase.DataResultDao().isExists(id)))
        } catch (exception: Exception) {
            emit(exceptionHandler<Boolean>(exception))
        }
    }

    fun insert(dataResult: DataResult, viewModelScope: CoroutineScope) {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.DataResultDao().insert(dataResult)
        }
    }

    fun remove(dataResult: DataResult, viewModelScope: CoroutineScope) {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.DataResultDao().remove(dataResult)
        }
    }
}
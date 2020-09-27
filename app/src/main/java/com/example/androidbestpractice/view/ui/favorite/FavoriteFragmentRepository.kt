package com.example.androidbestpractice.view.ui.favorite

import androidx.lifecycle.liveData
import com.example.androidbestpractice.base.BaseRepository
import com.example.androidbestpractice.di.networkManager.Resource
import com.example.androidbestpractice.entity.DataResult
import kotlinx.coroutines.Dispatchers

class FavoriteFragmentRepository : BaseRepository() {


    fun getFavorite() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            emit(Resource.success(appDatabase.DataResultDao().getAll()))
        } catch (exception: Exception) {
            emit(exceptionHandler<List<DataResult>>(exception))
        }
    }


}
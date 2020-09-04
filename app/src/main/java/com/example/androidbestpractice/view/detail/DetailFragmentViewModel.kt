package com.example.androidbestpractice.view.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidbestpractice.entity.DataResult
import org.koin.java.KoinJavaComponent.inject

class DetailFragmentViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val repository by inject(DetailFragmentRepository::class.java)
    private val dataResult = savedStateHandle.get<DataResult>("data")
        ?: throw IllegalArgumentException("missing data")

    val isExistsLiveData = repository.getExists(dataResult.id)

    fun insert() {
        repository.insert(dataResult, viewModelScope)
    }

    fun remove() {
        repository.remove(dataResult, viewModelScope)
    }


}
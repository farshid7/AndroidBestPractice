package com.example.androidbestpractice.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidbestpractice.di.networkManager.Resource
import com.example.androidbestpractice.entity.DataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class SearchFragmentViewModel : ViewModel() {
    private val repository by inject(SearchFragmentRepository::class.java)
    var query=""

    private val _searchLiveData = MutableLiveData<Resource<DataResponse>>()
    val searchLiveData: LiveData<Resource<DataResponse>> get() = _searchLiveData

    fun search() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.search(query,_searchLiveData)
        }
    }


}
package com.example.androidbestpractice.view.ui.home

import androidx.lifecycle.ViewModel
import org.koin.java.KoinJavaComponent.inject

class HomeFragmentViewModel:ViewModel(){
    private val repository by inject(HomeFragmentRepository::class.java)

    val discoverLiveData=repository.getDiscover()
}
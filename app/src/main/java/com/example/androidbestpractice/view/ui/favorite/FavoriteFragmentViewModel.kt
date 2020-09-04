package com.example.androidbestpractice.view.ui.favorite

import androidx.lifecycle.ViewModel
import org.koin.java.KoinJavaComponent.inject

class FavoriteFragmentViewModel : ViewModel() {
    private val repository by inject(FavoriteFragmentRepository::class.java)

    val favoriteLiveData = repository.getFavorite()


}
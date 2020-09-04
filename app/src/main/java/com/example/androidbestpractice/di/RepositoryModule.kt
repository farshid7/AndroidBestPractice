package com.example.androidbestpractice.di

import com.example.androidbestpractice.view.detail.DetailFragmentRepository
import com.example.androidbestpractice.view.ui.favorite.FavoriteFragmentRepository
import com.example.androidbestpractice.view.ui.home.HomeFragmentRepository
import com.example.androidbestpractice.view.ui.search.SearchFragmentRepository
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        single { HomeFragmentRepository() }
        single { SearchFragmentRepository() }
        single { DetailFragmentRepository() }
        single { FavoriteFragmentRepository() }
    }
}
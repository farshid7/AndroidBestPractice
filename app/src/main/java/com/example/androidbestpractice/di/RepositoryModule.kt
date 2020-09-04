package com.example.androidbestpractice.di

import com.example.androidbestpractice.ui.home.HomeFragmentRepository
import com.example.androidbestpractice.ui.search.SearchFragmentRepository
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        single { HomeFragmentRepository() }
        single { SearchFragmentRepository() }

    }
}
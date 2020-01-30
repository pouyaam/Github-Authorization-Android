package com.mydigipay.challenge.di

import com.mydigipay.challenge.presentation.ui.MainViewModel
import com.mydigipay.challenge.presentation.ui.github.search.SearchViewModel
import com.mydigipay.challenge.presentation.ui.github.GithubiViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        GithubiViewModel(
            get()
        )
    }
    viewModel { SearchViewModel(get()) }
    viewModel { MainViewModel(get()) }
}
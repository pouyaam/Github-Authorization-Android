package com.mydigipay.challenge.di

import com.mydigipay.challenge.presentation.ui.MainViewModel
import com.mydigipay.challenge.presentation.ui.github.GithubViewModel
import com.mydigipay.challenge.presentation.ui.github.detail.DetailViewModel
import com.mydigipay.challenge.presentation.ui.github.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GithubViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
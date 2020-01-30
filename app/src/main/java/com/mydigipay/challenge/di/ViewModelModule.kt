package com.mydigipay.challenge.di

import com.mydigipay.challenge.presentation.ui.github.search.SearchViewModel
import com.mydigipay.challenge.presentation.ui.login.LoginUriViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoginUriViewModel(get())
    }

    viewModel { SearchViewModel(get()) }
}
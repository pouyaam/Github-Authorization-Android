package com.mydigipay.challenge.ui.search.di

import com.mydigipay.challenge.ui.search.model.SearchModel
import com.mydigipay.challenge.ui.search.viewmodel.SearchViewModel
import org.koin.dsl.module

var searchModule = module {

    factory { SearchModel() }

    factory { SearchViewModel(get()) }

}
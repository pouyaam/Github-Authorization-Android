package com.mydigipay.challenge.di

import com.mydigipay.challenge.ui.search.SearchAdapter
import org.koin.dsl.module

val adapterModule = module {

    factory { SearchAdapter() }
}
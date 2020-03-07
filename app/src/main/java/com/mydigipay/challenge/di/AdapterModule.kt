package com.mydigipay.challenge.di

import com.mydigipay.challenge.ui.search.detail.CommitAdapter
import com.mydigipay.challenge.ui.search.list.SearchAdapter
import org.koin.dsl.module

val adapterModule = module {

    factory { SearchAdapter() }
    factory { CommitAdapter() }
}
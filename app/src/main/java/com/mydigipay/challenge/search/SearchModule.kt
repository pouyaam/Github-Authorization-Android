package com.mydigipay.challenge.search

import com.mydigipay.challenge.core.network.di.RETROFIT
import com.mydigipay.challenge.search.repository.SearchRepository
import com.mydigipay.challenge.search.repository.SearchRepositoryImp
import com.mydigipay.challenge.search.repository.remote.SearchService
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val searchModule = module {
    factory { get<Retrofit>(named(RETROFIT)).create(SearchService::class.java) }
    factory { SearchRepositoryImp(get()) as SearchRepository }

    viewModel { SearchViewModel(get()) }
}
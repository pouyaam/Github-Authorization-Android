package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.network.services.SearchService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {

    factory<SearchService> { get<Retrofit>(named(RETROFIT)).create(SearchService::class.java) }
}
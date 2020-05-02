package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.network.services.RepoService
import com.mydigipay.challenge.data.network.services.SearchService
import com.mydigipay.challenge.data.network.services.UserService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    factory<SearchService> { get<Retrofit>(named(RETROFIT)).create(SearchService::class.java) }
    factory<RepoService> { get<Retrofit>(named(RETROFIT)).create(RepoService::class.java) }
    factory<UserService> { get<Retrofit>(named(RETROFIT)).create(UserService::class.java) }
}
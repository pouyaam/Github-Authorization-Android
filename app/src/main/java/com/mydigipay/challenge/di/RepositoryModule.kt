package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.repository.*
import org.koin.dsl.module

val repositoryModule = module {

    single<TokenRepository> { TokenRepositoryImpl(get(), get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
}
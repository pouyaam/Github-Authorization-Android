package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.repository.SearchRepository
import com.mydigipay.challenge.data.repository.SearchRepositoryImpl
import com.mydigipay.challenge.data.repository.TokenRepository
import com.mydigipay.challenge.data.repository.TokenRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<TokenRepository> { TokenRepositoryImpl(get(), get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
}
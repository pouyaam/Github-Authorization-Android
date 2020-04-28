package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.repositories.search.SearchRepository
import com.mydigipay.challenge.data.repositories.search.SearchRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    factory<SearchRepository> { SearchRepositoryImp(get()) }
}
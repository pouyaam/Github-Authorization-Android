package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.repositories.repo.RepoRepository
import com.mydigipay.challenge.data.repositories.repo.RepoRepositoryImp
import com.mydigipay.challenge.data.repositories.search.SearchRepository
import com.mydigipay.challenge.data.repositories.search.SearchRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    factory<SearchRepository> { SearchRepositoryImp(get()) }
    factory<RepoRepository> { RepoRepositoryImp(get()) }
}
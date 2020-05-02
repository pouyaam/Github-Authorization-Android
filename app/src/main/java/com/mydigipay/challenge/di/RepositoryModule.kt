package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.repositories.repo.RepoRepository
import com.mydigipay.challenge.data.repositories.repo.RepoRepositoryImp
import com.mydigipay.challenge.data.repositories.search.SearchRepository
import com.mydigipay.challenge.data.repositories.search.SearchRepositoryImp
import com.mydigipay.challenge.data.repositories.user.UserRepository
import com.mydigipay.challenge.data.repositories.user.UserRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    factory<SearchRepository> { SearchRepositoryImp(get()) }
    factory<RepoRepository> { RepoRepositoryImp(get()) }
    factory<UserRepository> { UserRepositoryImp(get()) }
}
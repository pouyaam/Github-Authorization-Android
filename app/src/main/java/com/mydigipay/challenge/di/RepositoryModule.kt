package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.repositories.repo.RepoRepository
import com.mydigipay.challenge.data.repositories.repo.RepoRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    factory<RepoRepository> { RepoRepositoryImp(get()) }
}
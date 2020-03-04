package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.repository.gitrepo.GitRepoRepository
import com.mydigipay.challenge.data.repository.gitrepo.GitRepoRepositoryImpl
import com.mydigipay.challenge.data.repository.token.TokenRepository
import com.mydigipay.challenge.data.repository.token.TokenRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<TokenRepository> { TokenRepositoryImpl(get(), get()) }
    single<GitRepoRepository> { GitRepoRepositoryImpl(get()) }

}
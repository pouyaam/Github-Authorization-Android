package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.api.github.GithubService
import com.mydigipay.challenge.data.api.token.AccessTokenService
import com.mydigipay.challenge.data.local.LocalAccessTokenDataSourceImpl
import com.mydigipay.challenge.data.repository.github.GithubRepositoryImpl
import com.mydigipay.challenge.data.repository.github.RemoteGithubDataSourceImpl
import com.mydigipay.challenge.data.repository.token.RemoteAccessTokenDataSourceImpl
import com.mydigipay.challenge.data.repository.token.TokenRepositoryImpl
import com.mydigipay.challenge.domain.repositories.github.GithubRepository
import com.mydigipay.challenge.domain.repositories.github.RemoteGithubDataSource
import com.mydigipay.challenge.domain.repositories.token.LocalAccessTokenDataSource
import com.mydigipay.challenge.domain.repositories.token.RemoteAccessTokenDataSource
import com.mydigipay.challenge.domain.repositories.token.TokenRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoriesModule = module {
    // token repositories
    single { get<Retrofit>(named(RETROFIT)).create(AccessTokenService::class.java) }
    factory { RemoteAccessTokenDataSourceImpl(get()) as RemoteAccessTokenDataSource }
    factory { LocalAccessTokenDataSourceImpl(get()) as LocalAccessTokenDataSource }
    single { TokenRepositoryImpl(get(), get()) as TokenRepository }
    // github repositories
    single { get<Retrofit>(named(RETROFIT)).create(GithubService::class.java) }
    factory { RemoteGithubDataSourceImpl(get()) as RemoteGithubDataSource }
    factory { GithubRepositoryImpl(get()) as GithubRepository }
}
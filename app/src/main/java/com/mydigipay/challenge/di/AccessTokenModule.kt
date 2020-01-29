package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.AccessTokenService
import com.mydigipay.challenge.domain.repositories.token.remote.RemoteAccessTokenDataSource
import com.mydigipay.challenge.domain.repositories.token.remote.RemoteAccessTokenDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val accessTokenModule = module {
    factory { get<Retrofit>(named(RETROFIT)).create(AccessTokenService::class.java) }
    factory {
        RemoteAccessTokenDataSourceImpl(get()) as RemoteAccessTokenDataSource
    }
}
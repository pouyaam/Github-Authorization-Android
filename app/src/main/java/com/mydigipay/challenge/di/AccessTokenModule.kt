package com.mydigipay.challenge.di

import com.mydigipay.challenge.data.RemoteAccessTokenDataSourceImpl
import com.mydigipay.challenge.data.api.AccessTokenService
import com.mydigipay.challenge.data.local.LocalAccessTokenDataSourceImpl
import com.mydigipay.challenge.domain.repositories.token.LocalAccessTokenDataSource
import com.mydigipay.challenge.domain.repositories.token.RemoteAccessTokenDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val accessTokenModule = module {
    factory { get<Retrofit>(named(RETROFIT)).create(AccessTokenService::class.java) }
    factory {
        RemoteAccessTokenDataSourceImpl(get()) as RemoteAccessTokenDataSource
    }
    factory {
        LocalAccessTokenDataSourceImpl(get()) as LocalAccessTokenDataSource
    }
}
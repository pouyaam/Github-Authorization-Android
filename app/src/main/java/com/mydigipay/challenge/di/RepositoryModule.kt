package com.mydigipay.challenge.di

import com.mydigipay.challenge.repository.oauth.AccessTokenDataSource
import com.mydigipay.challenge.repository.oauth.AccessTokenDataSourceImpl
import com.mydigipay.challenge.repository.token.TokenRepository
import com.mydigipay.challenge.repository.token.TokenRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<TokenRepository> { TokenRepositoryImpl(get()) }
    factory<AccessTokenDataSource> { AccessTokenDataSourceImpl(get()) }
}
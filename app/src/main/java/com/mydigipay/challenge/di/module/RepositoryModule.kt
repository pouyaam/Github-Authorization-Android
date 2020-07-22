package com.mydigipay.challenge.di.module

import com.mydigipay.challenge.data.datasource.auth.LocalAccessTokenDataSource
import com.mydigipay.challenge.data.datasource.auth.RemoteAccessTokenDataSource
import com.mydigipay.challenge.data.repository.TokenRepositoryImpl
import com.mydigipay.challenge.domain.repository.TokenRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun ProvideTokenRepository(localAccessTokenDataSource: LocalAccessTokenDataSource , remoteAccessTokenDataSource: RemoteAccessTokenDataSource): TokenRepository {
        return TokenRepositoryImpl(localAccessTokenDataSource , remoteAccessTokenDataSource)
    }
}
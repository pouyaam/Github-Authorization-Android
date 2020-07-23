package com.mydigipay.challenge.di.module

import com.mydigipay.challenge.data.datasource.local.LocalAccessTokenDataSource
import com.mydigipay.challenge.data.datasource.remote.RemoteAccessTokenDataSource
import com.mydigipay.challenge.data.datasource.remote.SearchDataSource
import com.mydigipay.challenge.data.repository.SearchRepositoryImpl
import com.mydigipay.challenge.data.repository.TokenRepositoryImpl
import com.mydigipay.challenge.domain.repository.SearchRepository
import com.mydigipay.challenge.domain.repository.TokenRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun ProvideTokenRepository(
        localAccessTokenDataSource: LocalAccessTokenDataSource,
        remoteAccessTokenDataSource: RemoteAccessTokenDataSource
    ): TokenRepository {
        return TokenRepositoryImpl(localAccessTokenDataSource, remoteAccessTokenDataSource)
    }

    @Provides
    fun ProvideSearchRepository(searchDataSource: SearchDataSource): SearchRepository {
        return SearchRepositoryImpl(searchDataSource)
    }
}
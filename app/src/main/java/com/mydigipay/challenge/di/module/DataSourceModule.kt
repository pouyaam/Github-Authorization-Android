package com.mydigipay.challenge.di.module

import android.content.SharedPreferences
import com.mydigipay.challenge.data.datasource.local.LocalAccessTokenDataSource
import com.mydigipay.challenge.data.datasource.remote.RemoteAccessTokenDataSource
import com.mydigipay.challenge.data.datasource.api.ApiService
import com.mydigipay.challenge.data.datasource.remote.GithubDataSource
import com.mydigipay.challenge.datasource.local.LocalAccessTokenDataSourceImpl
import com.mydigipay.challenge.datasource.remote.RemoteAccessTokenDataSourceImpl
import com.mydigipay.challenge.datasource.remote.GithubDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    fun provideLocalAccessTokenDataSource(sharedPreferences: SharedPreferences): LocalAccessTokenDataSource {
        return LocalAccessTokenDataSourceImpl(
            sharedPreferences
        )
    }

    @Provides
    fun provideRemoteAccessTokenDataSource(apiService: ApiService): RemoteAccessTokenDataSource {
        return RemoteAccessTokenDataSourceImpl(
            apiService
        )
    }

    @Provides
    fun provideGithubDataSource(apiService: ApiService): GithubDataSource {
        return GithubDataSourceImpl(apiService)
    }
}
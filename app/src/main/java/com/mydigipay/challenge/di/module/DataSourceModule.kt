package com.mydigipay.challenge.di.module

import android.content.SharedPreferences
import com.mydigipay.challenge.data.datasource.local.LocalAccessTokenDataSource
import com.mydigipay.challenge.data.datasource.remote.RemoteAccessTokenDataSource
import com.mydigipay.challenge.data.datasource.api.ApiService
import com.mydigipay.challenge.data.datasource.remote.SearchDataSource
import com.mydigipay.challenge.datasource.local.LocalAccessTokenDataSourceImpl
import com.mydigipay.challenge.datasource.remote.RemoteAccessTokenDataSourceImpl
import com.mydigipay.challenge.datasource.remote.SearchDataSourceImpl
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
    fun provideSearchDataSource(apiService: ApiService): SearchDataSource {
        return SearchDataSourceImpl(apiService)
    }
}
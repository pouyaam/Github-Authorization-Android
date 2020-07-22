package com.mydigipay.challenge.di.module

import android.content.SharedPreferences
import com.mydigipay.challenge.data.datasource.auth.LocalAccessTokenDataSource
import com.mydigipay.challenge.data.datasource.auth.RemoteAccessTokenDataSource
import com.mydigipay.challenge.data.datasource.remote.ApiService
import com.mydigipay.challenge.datasource.auth.LocalAccessTokenDataSourceImpl
import com.mydigipay.challenge.datasource.auth.RemoteAccessTokenDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    fun provideLocalAccessTokenDataSource(sharedPreferences: SharedPreferences): LocalAccessTokenDataSource {
        return LocalAccessTokenDataSourceImpl(sharedPreferences)
    }

    @Provides
    fun provideRemoteAccessTokenDataSource(apiService: ApiService): RemoteAccessTokenDataSource {
        return RemoteAccessTokenDataSourceImpl(apiService)
    }

}
package com.mydigipay.challenge.framework.network

import com.mydigipay.challenge.ACCESS_TOKEN_URL
import com.mydigipay.challenge.REST_API_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * AccessToken
     */

    @Provides
    @Singleton
    @Named("access_token")
    fun provideOkHttpClientForAccessToken(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("access_token")
    fun provideRetrofitForAccessToken(@Named("access_token") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ACCESS_TOKEN_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRemoteAccessTokenService(@Named("access_token") retrofit: Retrofit): RemoteAccessTokenService {
        return retrofit.create(RemoteAccessTokenService::class.java)
    }

    /**
     * RestApi
     */

    @Provides
    @Singleton
    @Named("rest_api")
    fun provideOkHttpClientForRestApi(
        loggingInterceptor: HttpLoggingInterceptor,
        requestInterceptor: RequestInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(requestInterceptor)
            .build()
    }


    @Provides
    @Singleton
    @Named("rest_api")
    fun provideRetrofitForForRestApi(@Named("rest_api") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(REST_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

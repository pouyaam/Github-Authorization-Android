package com.mydigipay.challenge.di.module

import android.content.SharedPreferences
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mydigipay.challenge.data.datasource.api.ApiService
import com.mydigipay.challenge.datasource.local.TOKEN
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    private val requestTimeout = 60L
    private val baseUrl = "http://api.github.com/"
    private val authTokenKey = "Authorization"

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor
    ): OkHttpClient {

        return OkHttpClient().newBuilder()
            .connectTimeout(requestTimeout, TimeUnit.SECONDS)
            .readTimeout(requestTimeout, TimeUnit.SECONDS)
            .writeTimeout(requestTimeout, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideInterceptor(sharedPreferences: Lazy<SharedPreferences>): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val originalRequest = chain.request()
            val accessToken = sharedPreferences.get().getString(TOKEN, "") ?: ""

            if (alreadyHasAuthorizationHeader(originalRequest)) {
                chain.proceed(originalRequest)
            }

            val requestBuilder = originalRequest.newBuilder()
                .header(authTokenKey, "Bearer $accessToken")
                .method(originalRequest.method, originalRequest.body)

            chain.proceed(requestBuilder.build())
        }

    }

    private fun alreadyHasAuthorizationHeader(request: Request): Boolean {
        return !request.header(authTokenKey).isNullOrEmpty()
    }
}
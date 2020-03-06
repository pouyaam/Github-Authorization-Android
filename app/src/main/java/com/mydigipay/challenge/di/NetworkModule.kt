package com.mydigipay.challenge.di

import com.google.gson.GsonBuilder
import com.mydigipay.challenge.dataaccess.remote.GitApiService
import com.mydigipay.challenge.github.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

var networkModule = module {

    single {
        OkHttpClient().newBuilder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .also {
                if (BuildConfig.DEBUG && false) {
                    it.addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                }
            }
            .build()
    }

    single<Converter.Factory> {
        GsonConverterFactory.create(
            GsonBuilder()
                .setPrettyPrinting()
                .create()
        )
    }

    single<CallAdapter.Factory> {
        RxJava2CallAdapterFactory.create()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(get())
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .build()
    }

    factory<GitApiService> {
        get<Retrofit>().create(GitApiService::class.java)
    }
}
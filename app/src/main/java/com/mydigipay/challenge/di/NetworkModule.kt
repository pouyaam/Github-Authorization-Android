package com.mydigipay.challenge.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mydigipay.challenge.repository.token.TokenRepository
import com.mydigipay.challenge.repository.token.TokenRepositoryImpl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val OK_HTTP = "OK_HTTP"
const val RETROFIT = "RETROFIT"
const val READ_TIMEOUT = "READ_TIMEOUT"
const val WRITE_TIMEOUT = "WRITE_TIMEOUT"
const val CONNECTION_TIMEOUT = "CONNECTION_TIMEOUT"
val networkModule = module {

    single<Long>(named(READ_TIMEOUT)) { 30 * 1000 }
    single<Long>(named(WRITE_TIMEOUT)) { 10 * 1000 }
    single<Long>(named(CONNECTION_TIMEOUT)) { 10 * 1000 }

    factory<Interceptor> {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    factory(named(OK_HTTP)) {
        OkHttpClient.Builder()
            .readTimeout(get(named(READ_TIMEOUT)), TimeUnit.MILLISECONDS)
            .writeTimeout(get(named(WRITE_TIMEOUT)), TimeUnit.MILLISECONDS)
            .connectTimeout(get(named(CONNECTION_TIMEOUT)), TimeUnit.MILLISECONDS)
            .addInterceptor(get() as Interceptor)
            .build()
    }

    factory<Retrofit>(named(RETROFIT)) {
        Retrofit.Builder()
            .client(get(named(OK_HTTP)))
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single {
        TokenRepositoryImpl(get()) as TokenRepository
    }
}
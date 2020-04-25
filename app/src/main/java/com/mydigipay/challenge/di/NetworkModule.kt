package com.mydigipay.challenge.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mydigipay.challenge.network.di.OK_HTTP
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val READ_TIMEOUT = "READ_TIMEOUT"
const val WRITE_TIMEOUT = "WRITE_TIMEOUT"
const val CONNECTION_TIMEOUT = "CONNECTION_TIMEOUT"
val networkModule = module {

    single(named(READ_TIMEOUT)) { 30 * 1000 }
    single(named(WRITE_TIMEOUT)) { 10 * 1000 }
    single(named(CONNECTION_TIMEOUT)) { 10 * 1000 }

    factory { provideLoggingInterceptor() }
    factory {
        provideOkHttpClient(
            get(),
            get(named(com.mydigipay.challenge.network.di.READ_TIMEOUT)),
            get(named(com.mydigipay.challenge.network.di.WRITE_TIMEOUT)),
            get(named(com.mydigipay.challenge.network.di.CONNECTION_TIMEOUT))
        )
    }
    factory {
        provideRetrofit(get())
    }
}


fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.HEADERS)
        .setLevel(HttpLoggingInterceptor.Level.BODY)
}

fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    readTimeout: Long,
    writeTimeout: Long,
    connectionTimeout: Long
): OkHttpClient {
    return OkHttpClient.Builder()
        .readTimeout(
            readTimeout,
            TimeUnit.MILLISECONDS
        )
        .writeTimeout(
            writeTimeout,
            TimeUnit.MILLISECONDS
        )
        .connectTimeout(
            connectionTimeout,
            TimeUnit.MILLISECONDS
        )
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("http://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}
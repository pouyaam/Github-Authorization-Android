package com.mydigipay.challenge.network.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mydigipay.challenge.github.BuildConfig
import com.mydigipay.challenge.utils.getToken
import com.mydigipay.challenge.ui.home.HomeRepository
import com.mydigipay.challenge.ui.home.HomeRepositoryImpl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
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

    single(named(READ_TIMEOUT)) { 30 * 1000 }
    single(named(WRITE_TIMEOUT)) { 10 * 1000 }
    single(named(CONNECTION_TIMEOUT)) { 10 * 1000 }

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
            .addLogger()
            .addInterceptor()
            .addInterceptor(get())
            .build()
    }

    single(named(RETROFIT)) {
        Retrofit.Builder()
            .client(get(named(OK_HTTP)))
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }


    single {
        HomeRepositoryImpl(get(), get()) as HomeRepository
    }
}


fun OkHttpClient.Builder.addLogger() = apply {
    if (BuildConfig.DEBUG) {
        addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }
}

fun OkHttpClient.Builder.addInterceptor() = apply {
    addInterceptor { chain ->
        //        try {
        handleChain(chain)
//        } catch (ioException: UnknownHostException) {
//            throw  IOException(application.getString(R.string.connectionError), ioException)
//        } catch (ioException: IOException) {
//            throw  IOException(application.getString(R.string.socketError), ioException)
//        }
    }
}

private fun handleChain(chain: Interceptor.Chain): Response {
    return chain.request()
        .newBuilder()
        .addToken()
        .build().let {
            chain.proceed(it)
        }
}


private fun Request.Builder.addToken() = apply {
    getToken()?.takeIf { it.isNotEmpty() }?.let {
        addHeader("Authorization", "token $it")
    }
}

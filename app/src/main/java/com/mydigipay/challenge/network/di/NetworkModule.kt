package com.mydigipay.challenge.network.di

import android.app.Application
import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mydigipay.challenge.app.APPLICATION_CONTEXT
import com.mydigipay.challenge.github.BuildConfig
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.ui.commits.CommitRepository
import com.mydigipay.challenge.ui.commits.CommitRepositoryImpl
import com.mydigipay.challenge.ui.home.HomeRepository
import com.mydigipay.challenge.ui.home.HomeRepositoryImpl
import com.mydigipay.challenge.ui.profile.ProfileRepository
import com.mydigipay.challenge.ui.profile.ProfileRepositoryImpl
import com.mydigipay.challenge.utils.token
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

const val OK_HTTP_LOGGER = "OK_HTTP_LOGGER"
const val OK_HTTP = "OK_HTTP"
const val RETROFIT = "RETROFIT"
const val READ_TIMEOUT = "READ_TIMEOUT"
const val WRITE_TIMEOUT = "WRITE_TIMEOUT"
const val CONNECTION_TIMEOUT = "CONNECTION_TIMEOUT"
val networkModule = module {

    single(named(READ_TIMEOUT)) { 30 * 1000 }
    single(named(WRITE_TIMEOUT)) { 10 * 1000 }
    single(named(CONNECTION_TIMEOUT)) { 10 * 1000 }

    factory(named(OK_HTTP_LOGGER)) {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    single(named(OK_HTTP)) {
        OkHttpClient.Builder()
            .readTimeout(get(named(READ_TIMEOUT)), TimeUnit.MILLISECONDS)
            .writeTimeout(get(named(WRITE_TIMEOUT)), TimeUnit.MILLISECONDS)
            .connectTimeout(get(named(CONNECTION_TIMEOUT)), TimeUnit.MILLISECONDS)
            .addInterceptor(get(named(APPLICATION_CONTEXT)) as Context)
            .addInterceptor(get<HttpLoggingInterceptor>(named(OK_HTTP_LOGGER)))
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
        HomeRepositoryImpl(get()) as HomeRepository
    }
    single {
        CommitRepositoryImpl(get()) as CommitRepository
    }
    single {
        ProfileRepositoryImpl(get()) as ProfileRepository
    }
}


fun OkHttpClient.Builder.addLogger() = apply {
    if (BuildConfig.DEBUG) {
        addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }
}

fun OkHttpClient.Builder.addInterceptor(context: Context) = apply {
    addInterceptor { chain ->
        try {
            handleChain(chain)
        } catch (ioException: UnknownHostException) {
            throw  IOException(context.getString(R.string.connectionError), ioException)
        } catch (ioException: IOException) {
            throw  IOException(context.getString(R.string.socketError), ioException)
        }
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
    token?.takeIf { it.isNotEmpty() }?.let {
        addHeader("Authorization", "token $it")
    }
}

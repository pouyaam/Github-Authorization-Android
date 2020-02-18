package com.mydigipay.challenge.github

import androidx.preference.PreferenceManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mydigipay.challenge.network.di.*
import com.mydigipay.challenge.network.oauth.GithubApiService
import com.mydigipay.challenge.ui.commits.CommitRepository
import com.mydigipay.challenge.ui.commits.CommitRepositoryImpl
import com.mydigipay.challenge.ui.commits.CommitsViewModel
import com.mydigipay.challenge.ui.home.HomeRepository
import com.mydigipay.challenge.ui.home.HomeRepositoryImpl
import com.mydigipay.challenge.ui.home.HomeViewModel
import com.mydigipay.challenge.ui.profile.ProfileRepository
import com.mydigipay.challenge.ui.profile.ProfileRepositoryImpl
import com.mydigipay.challenge.ui.profile.ProfileViewModel
import com.mydigipay.challenge.utils.EventBus
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appTestModule = module {
    factory { HomeRepositoryImpl(get()) }
    single { EventBus.instance }
    single { PreferenceManager.getDefaultSharedPreferences(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { CommitsViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}

val networkTestModule= module {
    single {
        HomeRepositoryImpl(get()) as HomeRepository
    }
    single {
        CommitRepositoryImpl(get()) as CommitRepository
    }
    single {
        ProfileRepositoryImpl(get()) as ProfileRepository
    }

    factory { get<Retrofit>(named(RETROFIT)).create(GithubApiService::class.java) }


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
}


package com.mydigipay.challenge.di

import android.content.Context
import android.content.SharedPreferences
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {

    factory {
        CompositeDisposable()
    }

    single<SharedPreferences> {
        androidApplication().getSharedPreferences("App", Context.MODE_PRIVATE)
    }

}
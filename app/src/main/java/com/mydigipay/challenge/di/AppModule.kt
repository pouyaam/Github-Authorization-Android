package com.mydigipay.challenge.di

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.mydigipay.challenge.data.persist.SharedPrefWrapper
import com.mydigipay.challenge.data.persist.SharedPrefWrapperImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {

    single<SharedPreferences> {
        PreferenceManager.getDefaultSharedPreferences(androidApplication())
    }

    single<SharedPrefWrapper> {
        SharedPrefWrapperImpl(get())
    }

}
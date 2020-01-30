package com.mydigipay.challenge.di

import androidx.preference.PreferenceManager
import org.koin.dsl.module

val appModule = module {
    single { PreferenceManager.getDefaultSharedPreferences(get()) }
}

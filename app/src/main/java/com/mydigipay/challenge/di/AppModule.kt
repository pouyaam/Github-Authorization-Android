package com.mydigipay.challenge.di

import androidx.preference.PreferenceManager
import com.mydigipay.challenge.repository.token.TokenRepositoryImpl
import com.mydigipay.challenge.util.CashSetting
import com.mydigipay.challenge.util.CashSettingBySharedPref
import org.koin.dsl.module

val appModule = module {
    factory { TokenRepositoryImpl(get()) }
    single { PreferenceManager.getDefaultSharedPreferences(get()) }
    factory<CashSetting> { CashSettingBySharedPref(get()) }
}
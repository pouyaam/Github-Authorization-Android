package com.mydigipay.challenge.di

import androidx.preference.PreferenceManager
import com.mydigipay.challenge.data.setting.SettingBySharedPref
import com.mydigipay.challenge.util.Setting
import org.koin.dsl.module

val appModule = module {
    single { PreferenceManager.getDefaultSharedPreferences(get()) }
    factory<Setting> { SettingBySharedPref(get()) }
}
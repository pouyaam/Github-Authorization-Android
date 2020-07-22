package com.mydigipay.challenge.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class SharedPrefsModule {

    @Provides
    fun provideSharedPrefs(context: Context): SharedPreferences? {
        return context.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor? {
        return sharedPreferences.edit()
    }

}
package com.mydigipay.challenge.infrastructure.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mydigipay.challenge.infrastructure.data.DataManager
import com.mydigipay.challenge.infrastructure.data.DataManagerImp
import com.mydigipay.challenge.infrastructure.data.local.preference.PreferenceHelper
import com.mydigipay.challenge.infrastructure.data.local.preference.PreferenceHelperImp
import com.mydigipay.challenge.infrastructure.data.remote.ApiHelper
import com.mydigipay.challenge.infrastructure.data.remote.ApiHelperImp
import com.mydigipay.challenge.infrastructure.utils.AppSchedulerProvider
import com.mydigipay.challenge.infrastructure.utils.SchedulersProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideParser(): Gson {
        return GsonBuilder().create()
    }


    @Provides
    fun provideSchedulerProvider(): SchedulersProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Singleton
    fun provideDataManager(dataManagerImp: DataManagerImp): DataManager {
        return dataManagerImp
    }

    @Provides
    @Singleton
    fun providePreferencesHelper(preferenceHelperImp: PreferenceHelperImp): PreferenceHelper {
        return preferenceHelperImp
    }

    @Provides
    @Singleton
    internal fun provideApiHelper(): ApiHelper {
        return ApiHelperImp()
    }
}
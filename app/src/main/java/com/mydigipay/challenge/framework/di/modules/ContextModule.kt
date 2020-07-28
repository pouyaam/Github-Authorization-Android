package com.mydigipay.challenge.framework.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ContextModule {

    @Singleton
    @Binds
    abstract fun context(application: Application): Context
}

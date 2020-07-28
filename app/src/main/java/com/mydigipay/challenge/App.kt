package com.mydigipay.challenge

import android.app.Activity
import android.app.Application
import com.mydigipay.challenge.framework.di.components.AppComponent
import com.mydigipay.challenge.framework.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        mInjector = DaggerAppComponent.builder()
            .app(this)
            .build()
        mInjector.inject(this)
    }

    companion object {
        private lateinit var mInjector: AppComponent
    }
}

package com.mydigipay.challenge

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.mydigipay.challenge.infrastructure.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/3/2020.
 *
 * mahdiZTD@gmail.com
 */
class InterviewApp : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}
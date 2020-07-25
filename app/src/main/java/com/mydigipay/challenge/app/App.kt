package com.mydigipay.challenge.app

import android.app.Application
import com.mydigipay.challenge.di.component.AppComponent
import com.mydigipay.challenge.di.component.DaggerAppComponent

lateinit var component: AppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        component = DaggerAppComponent.factory().create(this)
    }

}
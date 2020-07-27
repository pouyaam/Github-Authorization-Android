package com.mydigipay.challenge.presentation.view.di

import androidx.fragment.app.FragmentManager
import com.mydigipay.challenge.framework.di.scopes.ActivityScope
import com.mydigipay.challenge.presentation.view.ui.AccessTokenActivity
import dagger.Module
import dagger.Provides

@Module
class AccessTokenActivityModule {

    @Provides
    @ActivityScope
    fun provideFragmentManager(activity: AccessTokenActivity): FragmentManager {
        return activity.supportFragmentManager
    }

    /* TODO("Provide other activity dependencies here") */
}

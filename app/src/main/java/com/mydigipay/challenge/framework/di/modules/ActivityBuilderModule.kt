package com.mydigipay.challenge.framework.di.modules

import com.mydigipay.challenge.framework.di.scopes.ActivityScope
import com.mydigipay.challenge.presentation.view.di.AccessTokenActivityModule
import com.mydigipay.challenge.presentation.view.di.GithubReposActivityModule
import com.mydigipay.challenge.presentation.view.ui.AccessTokenActivity
import com.mydigipay.challenge.presentation.view.ui.GithubReposActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [AccessTokenActivityModule::class])
    abstract fun bindAccessTokenActivity(): AccessTokenActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [GithubReposActivityModule::class])
    abstract fun bindGithubReposActivity(): GithubReposActivity
}

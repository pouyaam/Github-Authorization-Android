package com.mydigipay.challenge.presentation.view.di

import androidx.fragment.app.FragmentManager
import com.mydigipay.challenge.framework.di.scopes.ActivityScope
import com.mydigipay.challenge.presentation.view.ui.GithubReposActivity
import com.mydigipay.challenge.presentation.view.ui.GithubReposFragment
import com.mydigipay.challenge.presentation.view.ui.RepoCommitsFragment
import dagger.Module
import dagger.Provides

@Module
class GithubReposActivityModule {

    @Provides
    @ActivityScope
    fun provideFragmentManager(activity: GithubReposActivity): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides
    @ActivityScope
    fun provideGithubReposFragment(): GithubReposFragment {
        return GithubReposFragment.newInstance()
    }

    @Provides
    @ActivityScope
    fun provideRepoCommitsFragment(): RepoCommitsFragment {
        return RepoCommitsFragment()
    }

    /* TODO("Provide other activity dependencies here") */
}

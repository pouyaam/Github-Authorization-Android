package com.mydigipay.challenge.presentation.view.di

import androidx.fragment.app.FragmentManager
import com.mydigipay.challenge.framework.di.scopes.FragmentScope
import com.mydigipay.challenge.presentation.view.ui.GithubReposFragment
import dagger.Module
import dagger.Provides

@Module
class GithubReposFragmentModule {

    @Provides
    @FragmentScope
    fun provideFragmentManager(fragment: GithubReposFragment): FragmentManager {
        return fragment.childFragmentManager
    }

    // TODO("Provide other fragment dependencies here...")
}

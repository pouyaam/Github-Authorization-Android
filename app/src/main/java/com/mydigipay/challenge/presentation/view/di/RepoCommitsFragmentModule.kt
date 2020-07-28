package com.mydigipay.challenge.presentation.view.di

import androidx.fragment.app.FragmentManager
import com.mydigipay.challenge.framework.di.scopes.FragmentScope
import com.mydigipay.challenge.presentation.view.ui.RepoCommitsFragment
import dagger.Module
import dagger.Provides

@Module
class RepoCommitsFragmentModule {

    @Provides
    @FragmentScope
    fun provideFragmentManager(fragment: RepoCommitsFragment): FragmentManager {
        return fragment.childFragmentManager
    }

    // TODO("Provide other fragment dependencies here...")
}

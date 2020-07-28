package com.mydigipay.challenge.framework.di.modules

import com.mydigipay.challenge.framework.di.scopes.FragmentScope
import com.mydigipay.challenge.presentation.view.di.GithubReposFragmentModule
import com.mydigipay.challenge.presentation.view.di.RepoCommitsFragmentModule
import com.mydigipay.challenge.presentation.view.ui.GithubReposFragment
import com.mydigipay.challenge.presentation.view.ui.RepoCommitsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [GithubReposFragmentModule::class])
    abstract fun bindGithubReposFragment(): GithubReposFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [RepoCommitsFragmentModule::class])
    abstract fun bindRepoCommitsFragment(): RepoCommitsFragment
}

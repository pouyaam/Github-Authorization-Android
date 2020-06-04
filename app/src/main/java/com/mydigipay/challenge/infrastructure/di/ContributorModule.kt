package com.mydigipay.challenge.infrastructure.di

import com.mydigipay.challenge.mvvm.mainactivity.MainActivity
import com.mydigipay.challenge.mvvm.mainactivity.MainModule
import com.mydigipay.challenge.mvvm.repocommitsfragment.RepositoryCommitsFragment
import com.mydigipay.challenge.mvvm.repocommitsfragment.RepositoryCommitsModule
import com.mydigipay.challenge.mvvm.repositorysearchfragment.RepositorySearchFragment
import com.mydigipay.challenge.mvvm.repositorysearchfragment.RepositorySearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ContributorModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [RepositorySearchModule::class])
    abstract fun bindRepositorySearchFragment(): RepositorySearchFragment

    @ContributesAndroidInjector(modules = [RepositoryCommitsModule::class])
    abstract fun bindRepositoryCommitsFragment(): RepositoryCommitsFragment
//
//    @ContributesAndroidInjector(modules = [ArticleModule::class])
//    abstract fun bindArticleFragment(): ArticleFragment

}
package com.mydigipay.challenge.di

import com.mydigipay.challenge.dataaccess.CommitDataSource
import com.mydigipay.challenge.dataaccess.ProjectDataSource
import com.mydigipay.challenge.dataaccess.TokenDataSource
import com.mydigipay.challenge.dataaccess.UserDataSource
import com.mydigipay.challenge.dataaccess.cache.TokenCacheDataSource
import com.mydigipay.challenge.dataaccess.local.TokenLocalDataSource
import com.mydigipay.challenge.dataaccess.remote.CommitRemoteDataSource
import com.mydigipay.challenge.dataaccess.remote.ProjectRemoteDataSource
import com.mydigipay.challenge.dataaccess.remote.TokenRemoteDataSource
import com.mydigipay.challenge.dataaccess.remote.UserRemoteDataSource
import com.mydigipay.challenge.dataaccess.repository.CommitRepository
import com.mydigipay.challenge.dataaccess.repository.ProjectRepository
import com.mydigipay.challenge.dataaccess.repository.TokenRepository
import com.mydigipay.challenge.dataaccess.repository.UserRepository
import com.mydigipay.challenge.di.Qualifiers.commitDataSource
import com.mydigipay.challenge.di.Qualifiers.getNamedDependencyCache
import com.mydigipay.challenge.di.Qualifiers.getNamedDependencyLocal
import com.mydigipay.challenge.di.Qualifiers.getNamedDependencyRemote
import com.mydigipay.challenge.di.Qualifiers.getNamedDependencyRepository
import com.mydigipay.challenge.di.Qualifiers.projectDataSource
import com.mydigipay.challenge.di.Qualifiers.tokenDataSource
import com.mydigipay.challenge.di.Qualifiers.userDataSource
import org.koin.dsl.module

val dataAccessModule = module {

    single<TokenDataSource>(getNamedDependencyLocal(tokenDataSource)) {
        TokenLocalDataSource()
    }

    single<TokenDataSource>(getNamedDependencyCache(tokenDataSource)) {
        TokenCacheDataSource(get())
    }

    single<TokenDataSource>(getNamedDependencyRemote(tokenDataSource)) {
        TokenRemoteDataSource(get())
    }

    single<TokenDataSource>(getNamedDependencyRepository(tokenDataSource)) {
        TokenRepository(
            get(getNamedDependencyLocal(tokenDataSource)),
            get(getNamedDependencyCache(tokenDataSource)),
            get(getNamedDependencyRemote(tokenDataSource))
        )
    }

    single<ProjectDataSource>(getNamedDependencyRemote(projectDataSource)) {
        ProjectRemoteDataSource(get())
    }

    single<ProjectDataSource>(getNamedDependencyRepository(projectDataSource)) {
        ProjectRepository(get(getNamedDependencyRemote(projectDataSource)))
    }

    single<CommitDataSource>(getNamedDependencyRemote(commitDataSource)) {
        CommitRemoteDataSource(get())
    }

    single<CommitDataSource>(getNamedDependencyRepository(commitDataSource)) {
        CommitRepository(get(getNamedDependencyRemote(commitDataSource)))
    }

    single<UserDataSource>(getNamedDependencyRemote(userDataSource)) {
        UserRemoteDataSource(get())
    }

    single<UserDataSource>(getNamedDependencyRepository(userDataSource)) {
        UserRepository(get(getNamedDependencyRemote(userDataSource)))
    }
}
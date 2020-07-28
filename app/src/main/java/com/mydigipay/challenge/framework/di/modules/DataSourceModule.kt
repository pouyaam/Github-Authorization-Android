package  com.mydigipay.challenge.framework.di.modules

import com.mydigipay.challenge.authorization.AccessTokenDataSource
import com.mydigipay.challenge.framework.network.*
import com.mydigipay.challenge.framework.network.mapper.AccessTokenMapper
import com.mydigipay.challenge.framework.network.mapper.GithubRepoMapper
import com.mydigipay.challenge.framework.preference.LocalAccessTokenDataSource
import com.mydigipay.challenge.framework.preference.PreferenceModule
import com.mydigipay.challenge.framework.preference.TokenDao
import com.mydigipay.challenge.repositories.GithubRepoDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [PreferenceModule::class, NetworkModule::class])
class DataSourceModule {

    @Provides
    @Singleton
    @Named("remote")
    fun provideRemoteAccessTokenDataSource(
        service: RemoteAccessTokenService,
        mapper: AccessTokenMapper
    ): AccessTokenDataSource {
        return RemoteAccessTokenDataSource(service, mapper)
    }

    @Provides
    @Singleton
    @Named("local")
    fun provideLocalAccessTokenDataSource(
        service: TokenDao
    ): AccessTokenDataSource {
        return LocalAccessTokenDataSource(service)
    }

    @Provides
    @Singleton
    fun provideRemoteGithubRepoDataSource(
        service: RemoteGithubRepoService,
        mapper: GithubRepoMapper
    ): GithubRepoDataSource {
        return RemoteGithubRepoDataSource(service, mapper)
    }
}

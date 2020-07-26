package  com.mydigipay.challenge.framework.di.modules

import com.mydigipay.challenge.authorization.AccessTokenDataSource
import com.mydigipay.challenge.framework.network.NetworkModule
import com.mydigipay.challenge.framework.network.RemoteAccessTokenDataSource
import com.mydigipay.challenge.framework.network.RemoteAccessTokenService
import com.mydigipay.challenge.framework.network.mapper.AccessTokenMapper
import com.mydigipay.challenge.framework.preference.LocalAccessTokenDataSource
import com.mydigipay.challenge.framework.preference.PreferenceModule
import com.mydigipay.challenge.framework.preference.TokenDao
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
}

package com.mydigipay.challenge.presentation.github.datasource.remote

import com.mydigipay.challenge.data.datasource.api.ApiService
import com.mydigipay.challenge.data.datasource.remote.RemoteAccessTokenDataSource
import com.mydigipay.challenge.data.model.token.RequestAccessToken
import com.mydigipay.challenge.data.model.token.ResponseAccessToken
import com.mydigipay.challenge.datasource.remote.RemoteAccessTokenDataSourceImpl
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class RemoteAccessTokenDataSourceTest {

    private lateinit var remoteAccessTokenDataSource: RemoteAccessTokenDataSource
    private val compositeDisposable = CompositeDisposable()

    @Mock
    private lateinit var apiService: ApiService

    private val requestAccessToken = RequestAccessToken(
        clientId = "clientID",
        clientSecret = "clientSecret",
        code = "123456",
        redirectUri = "redirectUri",
        state = "state"
    )
    private val responseAccessToken = ResponseAccessToken(
        accessToken = "accessToken",
        tokenType = "myType"
    )

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        remoteAccessTokenDataSource = RemoteAccessTokenDataSourceImpl(apiService)
    }

    @Test
    fun getAccessTokenTest() {
        Mockito.`when`(apiService.getAccessToken(requestAccessToken))
            .thenReturn(Single.just(responseAccessToken))
        val getAccessTokenObserver =
            remoteAccessTokenDataSource.getAccessToken(
                requestAccessToken.clientId,
                requestAccessToken.clientSecret,
                requestAccessToken.code,
                requestAccessToken.redirectUri,
                requestAccessToken.state
            ).test()
        compositeDisposable.add(getAccessTokenObserver)
        getAccessTokenObserver.assertComplete()
        getAccessTokenObserver.assertNoErrors()
        getAccessTokenObserver.assertValue {
            it.accessToken.equals(responseAccessToken.accessToken) &&
                    it.tokenType.equals(responseAccessToken.tokenType)
        }
    }

    @After
    fun tearDown() {
        compositeDisposable.dispose()
    }
}
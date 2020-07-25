package com.mydigipay.challenge.presentation.github.datasource.local

import android.content.Context
import android.content.SharedPreferences
import com.mydigipay.challenge.data.datasource.local.LocalAccessTokenDataSource
import com.mydigipay.challenge.datasource.local.LocalAccessTokenDataSourceImpl
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LocalAccessTokenDataSourceTest {

    @Mock
    lateinit var sharedPreferences: SharedPreferences
    private val accessToken = "Token"
    lateinit var localAccessTokenDataSource: LocalAccessTokenDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        localAccessTokenDataSource = LocalAccessTokenDataSourceImpl(sharedPreferences)
    }

    @Test
    fun readTokenSuccessfully() {
        Mockito.`when`(sharedPreferences.getString(anyString(), anyString()))
            .thenReturn(accessToken)
        val token = localAccessTokenDataSource.readToken()
        assert(token.equals(accessToken))
    }

}
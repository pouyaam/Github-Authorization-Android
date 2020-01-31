package com.mydigipay.challenge.presentation.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mydigipay.challenge.data.repository.token.TokenRepositoryImpl
import com.mydigipay.challenge.domain.repositories.token.TokenRepository
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: TokenRepository = mockk<TokenRepositoryImpl>(relaxUnitFun = true)
    private val mainViewModel = MainViewModel(repository)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `test read token`() {
        every { repository.readAccessToken() } returns TokenFactory.token
        mainViewModel.readToken()
        verifySequence {
            repository.readAccessToken()
        }
    }
}
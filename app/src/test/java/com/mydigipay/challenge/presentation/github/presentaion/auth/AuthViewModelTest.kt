package com.mydigipay.challenge.presentation.github.presentaion.auth

import com.mydigipay.challenge.domain.usecase.AuthUseCase
import com.mydigipay.challenge.presentation.auth.AuthActivityState
import com.mydigipay.challenge.presentation.auth.AuthViewModel
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AuthViewModelTest {

    private lateinit var authViewModel: AuthViewModel
    private val compositeDisposable = CompositeDisposable()
    private val code = "code"

    @Mock
    private lateinit var useCase: AuthUseCase

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler {
            Schedulers.trampoline()
        }
        MockitoAnnotations.initMocks(this)
        authViewModel = AuthViewModel(useCase)
    }

    @Test
    fun userAuthorizationStatusTest() {
        var userAuthorizationStatus = false

        //User is authorized
        Mockito.`when`(useCase.isUserAuthorized()).thenReturn(true)
        userAuthorizationStatus = authViewModel.isUserAuthorized()
        assertTrue(userAuthorizationStatus)

        //User isn't authorized
        Mockito.`when`(useCase.isUserAuthorized()).thenReturn(false)
        userAuthorizationStatus = authViewModel.isUserAuthorized()
        assertFalse(userAuthorizationStatus)
    }

    @Test
    fun getAccessTokenSuccessfullyTest() {
        Mockito.`when`(useCase.fetchAccessToken(anyString())).thenReturn(Completable.complete())
        val stateObserver = authViewModel.getState().test()
        compositeDisposable.add(stateObserver)
        authViewModel.fetchAccessToken(code)

        assert(stateObserver.valueCount() == 2)
        stateObserver.assertValueAt(0, AuthActivityState.Loading)
        stateObserver.assertValueAt(
            1,
            AuthActivityState.SuccessfullyGotToken
        )
    }

    @Test
    fun getAccessTokenFailedTest() {
        Mockito.`when`(useCase.fetchAccessToken(anyString()))
            .thenReturn(Completable.error(Throwable()))
        val stateObserver = authViewModel.getState().test()
        compositeDisposable.add(stateObserver)
        authViewModel.fetchAccessToken(code)

        assert(stateObserver.valueCount() == 2)
        stateObserver.assertValueAt(0, AuthActivityState.Loading)
        stateObserver.assertValueAt(
            1,
            AuthActivityState.Error
        )
    }
}
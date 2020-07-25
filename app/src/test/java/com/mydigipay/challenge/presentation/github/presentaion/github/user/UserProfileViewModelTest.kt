package com.mydigipay.challenge.presentation.github.presentaion.github.user

import com.mydigipay.challenge.domain.model.User
import com.mydigipay.challenge.domain.model.mapToPresentationModel
import com.mydigipay.challenge.domain.usecase.UserUseCase
import com.mydigipay.challenge.presentation.github.user.UserProfileFragmentState
import com.mydigipay.challenge.presentation.github.user.UserProfileViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserProfileViewModelTest {

    private lateinit var userProfileViewModel: UserProfileViewModel
    private val compositeDisposable = CompositeDisposable()
    private val user = User(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )

    @Mock
    private lateinit var userUseCase: UserUseCase

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler {
            Schedulers.trampoline()
        }
        MockitoAnnotations.initMocks(this)
        userProfileViewModel = UserProfileViewModel(userUseCase)
    }

    @Test
    fun getUserSuccessfully() {
        Mockito.`when`(userUseCase.getUser()).thenReturn(
            Single.just(user)
        )
        val stateObserver = userProfileViewModel.getState().test()
        userProfileViewModel.fetchUserInfo()
        assert(stateObserver.valueCount() == 2)
        stateObserver.assertValueAt(0, UserProfileFragmentState.Loading)
        stateObserver.assertValueAt(
            1,
            UserProfileFragmentState.GotUser(user.mapToPresentationModel())
        )
    }

    @Test
    fun getUserFailed() {
        Mockito.`when`(userUseCase.getUser()).thenReturn(
            Single.error(Throwable())
        )
        val stateObserver = userProfileViewModel.getState().test()
        userProfileViewModel.fetchUserInfo()
        assert(stateObserver.valueCount() == 2)
        stateObserver.assertValueAt(0, UserProfileFragmentState.Loading)
        stateObserver.assertValueAt(
            1,
            UserProfileFragmentState.Error
        )
    }

    @After
    fun tearDown() {
        compositeDisposable.dispose()
    }
}
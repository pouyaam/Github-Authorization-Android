package com.mydigipay.challenge.mvvm.mainactivity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mydigipay.challenge.infrastructure.data.DataManager
import com.mydigipay.challenge.infrastructure.data.model.api.RequestAccessToken
import com.mydigipay.challenge.infrastructure.data.model.api.ResponseAccessToken
import com.mydigipay.challenge.infrastructure.utils.AppSchedulerProviderTest
import com.mydigipay.challenge.infrastructure.utils.Constants
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 *
 * mahdiZTD@gmail.com
 */

@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    lateinit var dataManager: DataManager

    lateinit var viewModel: MainViewModel

    private val testScheduler: TestScheduler = TestScheduler()

    private val request = RequestAccessToken(
        Constants.CLIENT_ID,
        Constants.CLIENT_SECRET,
        "4ef76a6723792d576a5e",
        Constants.CLIENT_REDIRECT_URI,
        "0"
    )

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val testSchedulerProvider = AppSchedulerProviderTest(testScheduler)
        viewModel = MainViewModel(dataManager, testSchedulerProvider)
    }

    @Test
    fun `test api helper is not null`() {
        Mockito.`when`(
            dataManager.accessToken(
                request
            )
        ).thenReturn(null)

    }

    @Test
    fun `test api helper have success response`() {
        val successResponse =
            ResponseAccessToken("token_sample", "")

        Mockito.`when`(dataManager.accessToken(request)).thenReturn(Single.just(successResponse))
        viewModel.getAccessToken("4ef76a6723792d576a5e")
        testScheduler.triggerActions()
        assertNotNull(successResponse)
    }

    @Test
    fun `test api helper have failed response`() {
        Mockito.`when`(dataManager.accessToken(request)).thenReturn(Single.error(Throwable("API ERROR")))
        viewModel.getAccessToken("4ef76a6723792d576a5e")
        testScheduler.triggerActions()
    }

    @After
    fun tearDown() {
        viewModel.compositeDisposable.dispose()
    }
}


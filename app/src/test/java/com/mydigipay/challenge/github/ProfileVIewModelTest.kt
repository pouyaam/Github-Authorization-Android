package com.mydigipay.challenge.github

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mydigipay.challenge.core.network.di.networkModule
import com.mydigipay.challenge.profile.ProfileViewModel
import com.mydigipay.challenge.profile.UserType
import com.mydigipay.challenge.profile.profileModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ProfileVIewModelTest : KoinTest {

    private val username = "mohammadnejad"
    private val profileViewModel: ProfileViewModel by inject()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        if (GlobalContext.getOrNull() == null) {
            startKoin {
                modules(
                    listOf(
                        networkModule,
                        profileModule
                    )
                )
            }
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun testGetUser() {
        mainCoroutineRule.pauseDispatcher()
        profileViewModel.getUserInfo(username, UserType.OtherUser.name, "")

        profileViewModel.userInfo.observeForTesting {
            mainCoroutineRule.resumeDispatcher()
            Assert.assertEquals(username, profileViewModel.userInfo.getOrAwaitValue()?.login)
        }
    }
}
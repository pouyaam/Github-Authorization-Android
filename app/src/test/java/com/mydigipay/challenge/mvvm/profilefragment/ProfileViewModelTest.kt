package com.mydigipay.challenge.mvvm.profilefragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.mydigipay.challenge.infrastructure.data.DataManager
import com.mydigipay.challenge.infrastructure.data.model.api.ResponseGithubProfile
import com.mydigipay.challenge.infrastructure.utils.AppSchedulerProviderTest
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Assert.*
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
class ProfileViewModelTest{

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    lateinit var dataManager: DataManager

    lateinit var viewModel: ProfileViewModel

    private val testScheduler: TestScheduler = TestScheduler()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val testSchedulerProvider = AppSchedulerProviderTest(testScheduler)
        viewModel = ProfileViewModel(dataManager, testSchedulerProvider)
    }

    @Test
    fun `test api helper is not null`() {
        Mockito.`when`(
            dataManager.getUserProfile(
                "username"
            )
        ).thenReturn(null)
        assertTrue(viewModel.name.get() == null)
        assertTrue(viewModel.avatar.get() == null)
        assertTrue(viewModel.bio.get() == null)
        assertTrue(viewModel.company.get() == null)
        assertTrue(viewModel.url.get() == null)
    }

    @Test
    fun `test api helper have success response`() {
        val successResponse =Gson().fromJson<ResponseGithubProfile>("{\n" +
                "  \"login\": \"lighthouse-labs\",\n" +
                "  \"id\": 5753105,\n" +
                "  \"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjU3NTMxMDU=\",\n" +
                "  \"avatar_url\": \"https://avatars0.githubusercontent.com/u/5753105?v=4\",\n" +
                "  \"gravatar_id\": \"\",\n" +
                "  \"url\": \"https://api.github.com/users/lighthouse-labs\",\n" +
                "  \"html_url\": \"https://github.com/lighthouse-labs\",\n" +
                "  \"followers_url\": \"https://api.github.com/users/lighthouse-labs/followers\",\n" +
                "  \"following_url\": \"https://api.github.com/users/lighthouse-labs/following{/other_user}\",\n" +
                "  \"gists_url\": \"https://api.github.com/users/lighthouse-labs/gists{/gist_id}\",\n" +
                "  \"starred_url\": \"https://api.github.com/users/lighthouse-labs/starred{/owner}{/repo}\",\n" +
                "  \"subscriptions_url\": \"https://api.github.com/users/lighthouse-labs/subscriptions\",\n" +
                "  \"organizations_url\": \"https://api.github.com/users/lighthouse-labs/orgs\",\n" +
                "  \"repos_url\": \"https://api.github.com/users/lighthouse-labs/repos\",\n" +
                "  \"events_url\": \"https://api.github.com/users/lighthouse-labs/events{/privacy}\",\n" +
                "  \"received_events_url\": \"https://api.github.com/users/lighthouse-labs/received_events\",\n" +
                "  \"type\": \"Organization\",\n" +
                "  \"site_admin\": false,\n" +
                "  \"name\": \"Lighthouse Labs\",\n" +
                "  \"company\": null,\n" +
                "  \"blog\": \"http://www.lighthouselabs.ca/\",\n" +
                "  \"location\": \"Canada\",\n" +
                "  \"email\": null,\n" +
                "  \"hireable\": null,\n" +
                "  \"bio\": \"Canada's Developer Bootcamp.\",\n" +
                "  \"twitter_username\": null,\n" +
                "  \"public_repos\": 176,\n" +
                "  \"public_gists\": 0,\n" +
                "  \"followers\": 0,\n" +
                "  \"following\": 0,\n" +
                "  \"created_at\": \"2013-10-23T04:55:25Z\",\n" +
                "  \"updated_at\": \"2020-01-04T00:00:17Z\"\n" +
                "}",ResponseGithubProfile::class.java)

        Mockito.`when`(dataManager.getUserProfile(
            "username"
        )).thenReturn(Single.just(successResponse))
        viewModel.getProfile("username")
        testScheduler.triggerActions()
        assertNotNull(successResponse)
        assertEquals(viewModel.name.get(),successResponse.name)
        assertEquals(viewModel.avatar.get(),successResponse.avatarUrl)
        assertEquals(viewModel.bio.get(),successResponse.bio)
        assertEquals(viewModel.company.get(),successResponse.company)
        assertEquals(viewModel.url.get(),successResponse.url)
    }

    @Test
    fun `test api helper have failed response`() {
        Mockito.`when`(dataManager.getUserProfile("username")).thenReturn(Single.error(Throwable("API ERROR")))
        viewModel.getProfile("username")
        testScheduler.triggerActions()
        assertTrue(viewModel.name.get() == null)
        assertTrue(viewModel.avatar.get() == null)
        assertTrue(viewModel.bio.get() == null)
        assertTrue(viewModel.company.get() == null)
        assertTrue(viewModel.url.get() == null)
    }

    @After
    fun tearDown() {
        viewModel.compositeDisposable.dispose()
    }
}
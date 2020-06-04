package com.mydigipay.challenge.mvvm.repocommitsfragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mydigipay.challenge.infrastructure.data.DataManager
import com.mydigipay.challenge.infrastructure.data.model.api.ResponseGithubProfile
import com.mydigipay.challenge.infrastructure.data.model.api.repositorycommit.ResponseRepositoryCommits
import com.mydigipay.challenge.infrastructure.utils.AppSchedulerProviderTest
import com.mydigipay.challenge.mvvm.profilefragment.ProfileViewModel
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.*
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
class RepositoryCommitsViewModelTest {

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var dataManager: DataManager

    lateinit var viewModel: RepositoryCommitsViewModel

    private val testScheduler: TestScheduler = TestScheduler()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val testSchedulerProvider = AppSchedulerProviderTest(testScheduler)
        viewModel = RepositoryCommitsViewModel(dataManager, testSchedulerProvider)
    }

    @Test
    fun `test api helper is not null`() {
        Mockito.`when`(
            dataManager.getRepositoryCommits(
                "login",
                "name"
            )
        ).thenReturn(null)
        Assert.assertTrue(viewModel.repoCommitsLiveData.value == null)
        Assert.assertTrue(viewModel.repositoryCommits.size == 0)
    }

    @Test
    fun `test api helper have success response`() {
        val successResponse: MutableList<ResponseRepositoryCommits> = Gson().fromJson(
            "[\n" +
                    "  {\n" +
                    "    \"sha\": \"3783d9b43ceb0403bc3b8b347150ef42f620d11b\",\n" +
                    "    \"node_id\": \"MDY6Q29tbWl0MzE1NTI5OTozNzgzZDliNDNjZWIwNDAzYmMzYjhiMzQ3MTUwZWY0MmY2MjBkMTFi\",\n" +
                    "    \"commit\": {\n" +
                    "      \"author\": {\n" +
                    "        \"name\": \"Martijn Courteaux\",\n" +
                    "        \"email\": \"courteauxmartijn@gmail.com\",\n" +
                    "        \"date\": \"2017-06-20T08:17:42Z\"\n" +
                    "      },\n" +
                    "      \"committer\": {\n" +
                    "        \"name\": \"Martijn Courteaux\",\n" +
                    "        \"email\": \"courteauxmartijn@gmail.com\",\n" +
                    "        \"date\": \"2017-06-20T08:17:42Z\"\n" +
                    "      },\n" +
                    "      \"message\": \"Removed another binary folder.\",\n" +
                    "      \"tree\": {\n" +
                    "        \"sha\": \"764349790f5bd33211a6633deae6c5138a1d35de\",\n" +
                    "        \"url\": \"https://api.github.com/repos/mcourteaux/CraftMania/git/trees/764349790f5bd33211a6633deae6c5138a1d35de\"\n" +
                    "      },\n" +
                    "      \"url\": \"https://api.github.com/repos/mcourteaux/CraftMania/git/commits/3783d9b43ceb0403bc3b8b347150ef42f620d11b\",\n" +
                    "      \"comment_count\": 0,\n" +
                    "      \"verification\": {\n" +
                    "        \"verified\": false,\n" +
                    "        \"reason\": \"unsigned\",\n" +
                    "        \"signature\": null,\n" +
                    "        \"payload\": null\n" +
                    "      }\n" +
                    "    },\n" +
                    "    \"url\": \"https://api.github.com/repos/mcourteaux/CraftMania/commits/3783d9b43ceb0403bc3b8b347150ef42f620d11b\",\n" +
                    "    \"html_url\": \"https://github.com/mcourteaux/CraftMania/commit/3783d9b43ceb0403bc3b8b347150ef42f620d11b\",\n" +
                    "    \"comments_url\": \"https://api.github.com/repos/mcourteaux/CraftMania/commits/3783d9b43ceb0403bc3b8b347150ef42f620d11b/comments\",\n" +
                    "    \"author\": {\n" +
                    "      \"login\": \"mcourteaux\",\n" +
                    "      \"id\": 845012,\n" +
                    "      \"node_id\": \"MDQ6VXNlcjg0NTAxMg==\",\n" +
                    "      \"avatar_url\": \"https://avatars2.githubusercontent.com/u/845012?v=4\",\n" +
                    "      \"gravatar_id\": \"\",\n" +
                    "      \"url\": \"https://api.github.com/users/mcourteaux\",\n" +
                    "      \"html_url\": \"https://github.com/mcourteaux\",\n" +
                    "      \"followers_url\": \"https://api.github.com/users/mcourteaux/followers\",\n" +
                    "      \"following_url\": \"https://api.github.com/users/mcourteaux/following{/other_user}\",\n" +
                    "      \"gists_url\": \"https://api.github.com/users/mcourteaux/gists{/gist_id}\",\n" +
                    "      \"starred_url\": \"https://api.github.com/users/mcourteaux/starred{/owner}{/repo}\",\n" +
                    "      \"subscriptions_url\": \"https://api.github.com/users/mcourteaux/subscriptions\",\n" +
                    "      \"organizations_url\": \"https://api.github.com/users/mcourteaux/orgs\",\n" +
                    "      \"repos_url\": \"https://api.github.com/users/mcourteaux/repos\",\n" +
                    "      \"events_url\": \"https://api.github.com/users/mcourteaux/events{/privacy}\",\n" +
                    "      \"received_events_url\": \"https://api.github.com/users/mcourteaux/received_events\",\n" +
                    "      \"type\": \"User\",\n" +
                    "      \"site_admin\": false\n" +
                    "    },\n" +
                    "    \"committer\": {\n" +
                    "      \"login\": \"mcourteaux\",\n" +
                    "      \"id\": 845012,\n" +
                    "      \"node_id\": \"MDQ6VXNlcjg0NTAxMg==\",\n" +
                    "      \"avatar_url\": \"https://avatars2.githubusercontent.com/u/845012?v=4\",\n" +
                    "      \"gravatar_id\": \"\",\n" +
                    "      \"url\": \"https://api.github.com/users/mcourteaux\",\n" +
                    "      \"html_url\": \"https://github.com/mcourteaux\",\n" +
                    "      \"followers_url\": \"https://api.github.com/users/mcourteaux/followers\",\n" +
                    "      \"following_url\": \"https://api.github.com/users/mcourteaux/following{/other_user}\",\n" +
                    "      \"gists_url\": \"https://api.github.com/users/mcourteaux/gists{/gist_id}\",\n" +
                    "      \"starred_url\": \"https://api.github.com/users/mcourteaux/starred{/owner}{/repo}\",\n" +
                    "      \"subscriptions_url\": \"https://api.github.com/users/mcourteaux/subscriptions\",\n" +
                    "      \"organizations_url\": \"https://api.github.com/users/mcourteaux/orgs\",\n" +
                    "      \"repos_url\": \"https://api.github.com/users/mcourteaux/repos\",\n" +
                    "      \"events_url\": \"https://api.github.com/users/mcourteaux/events{/privacy}\",\n" +
                    "      \"received_events_url\": \"https://api.github.com/users/mcourteaux/received_events\",\n" +
                    "      \"type\": \"User\",\n" +
                    "      \"site_admin\": false\n" +
                    "    },\n" +
                    "    \"parents\": [\n" +
                    "      {\n" +
                    "        \"sha\": \"1cf805673918a9265dc618b2798fda00bec134d1\",\n" +
                    "        \"url\": \"https://api.github.com/repos/mcourteaux/CraftMania/commits/1cf805673918a9265dc618b2798fda00bec134d1\",\n" +
                    "        \"html_url\": \"https://github.com/mcourteaux/CraftMania/commit/1cf805673918a9265dc618b2798fda00bec134d1\"\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "]", object : TypeToken<ArrayList<ResponseRepositoryCommits>>() {}.type
        )

        Mockito.`when`(
            dataManager.getRepositoryCommits(
                "login",
                "name"
            )
        ).thenReturn(Single.just(successResponse))
        viewModel.getRepoCommits("login", "name")
        testScheduler.triggerActions()
        Assert.assertNotNull(successResponse)
        Assert.assertEquals(viewModel.repoCommitsLiveData.value, successResponse)
    }

    @Test
    fun `test api helper have failed response`() {
        Mockito.`when`(dataManager.getRepositoryCommits("login", "name"))
            .thenReturn(Single.error(Throwable("API ERROR")))
        viewModel.getRepoCommits("login", "name")
        testScheduler.triggerActions()
        Assert.assertTrue(viewModel.repoCommitsLiveData.value == null)
        Assert.assertTrue(viewModel.repositoryCommits.size == 0)
    }

    @After
    fun tearDown() {
        viewModel.compositeDisposable.dispose()
    }
}
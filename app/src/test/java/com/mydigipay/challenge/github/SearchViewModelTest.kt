package com.mydigipay.challenge.github

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mydigipay.challenge.core.network.di.networkModule
import com.mydigipay.challenge.profile.profileModule
import com.mydigipay.challenge.search.SearchViewModel
import com.mydigipay.challenge.search.repository.model.RepositoryModel
import com.mydigipay.challenge.search.searchModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SearchViewModelTest : KoinTest {

    private val searchKey = "navigation"
    private val searchViewModel: SearchViewModel by inject()

    @Mock
    lateinit var repositoriesObserver: Observer<List<RepositoryModel>>

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        if (GlobalContext.getOrNull() == null) {
            startKoin {
                modules(
                    listOf(
                        networkModule,
                        searchModule
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
    fun testGetSearchList() {
        searchViewModel.repositories.observeForever(repositoriesObserver)

        mainCoroutineRule.pauseDispatcher()
        searchViewModel.getRepositories(searchKey)

        mainCoroutineRule.resumeDispatcher()
        val list = searchViewModel.repositories.getOrAwaitValue()
        Mockito.verify(repositoriesObserver).onChanged(list)
    }
}
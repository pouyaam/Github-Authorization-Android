package com.mydigipay.challenge.presentation.github.presentaion.github.search

import com.mydigipay.challenge.domain.model.RemoteRepository
import com.mydigipay.challenge.domain.model.RemoteRepositoryOwner
import com.mydigipay.challenge.domain.model.mapToPresentationModel
import com.mydigipay.challenge.domain.usecase.SearchUseCase
import com.mydigipay.challenge.presentation.github.search.SearchFragmentState
import com.mydigipay.challenge.presentation.github.search.SearchViewModel
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

class SearchViewModelTest {
    private lateinit var searchViewModel: SearchViewModel
    private val compositeDisposable = CompositeDisposable()
    private val correctSearchQuery = "query"
    private val emptySearchQuery = ""
    private val remoteRepositoryOwner = RemoteRepositoryOwner(
        login = "login",
        nodeId = "nodeId",
        id = 132,
        avatarUrl = "avatarUrl",
        gravatarId = "gravatarId",
        url = "url",
        receivedEventsUrl = "receivedEventsUrl",
        type = "type"
    )
    private val remoteRepository = RemoteRepository(
        id = 123,
        nodeId = "nodeID",
        name = "MyRepo",
        fullName = "Einhesari/MyRepo",
        remoteRepositoryOwner = remoteRepositoryOwner,
        url = "url",
        defaultBranch = "master",
        isPrivate = false,
        htmlUrl = "htmlUrl",
        description = "description",
        isFork = false,
        createdAt = "date",
        updatedAt = "date",
        pushedAt = "date",
        homepage = "homepage",
        size = 1,
        stargazersCount = 1,
        watchersCount = null,
        language = null,
        forksCount = null,
        openIssuesCount = null,
        masterBranch = null,
        score = null
    )

    @Mock
    private lateinit var useCase: SearchUseCase

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler {
            Schedulers.trampoline()
        }
        MockitoAnnotations.initMocks(this)
        searchViewModel = SearchViewModel(useCase)
    }

    @Test
    fun searchSuccessfulTest() {
        Mockito.`when`(useCase.searchRepository(correctSearchQuery))
            .thenReturn(Single.just(listOf(remoteRepository)))
        val stateObserver = searchViewModel.getState().test()
        compositeDisposable.add(stateObserver)
        searchViewModel.searchRepository(correctSearchQuery)

        assert(stateObserver.valueCount() == 2)
        stateObserver.assertValueAt(0, SearchFragmentState.Loading)
        stateObserver.assertValueAt(
            1,
            SearchFragmentState.SearchedRepository(listOf(remoteRepository.mapToPresentationModel()))
        )

    }

    @Test
    fun searchEmptyQueryTest() {
        Mockito.`when`(useCase.searchRepository(emptySearchQuery))
            .thenReturn(Single.error(Throwable("422")))
        val stateObserver = searchViewModel.getState().test()
        compositeDisposable.add(stateObserver)
        searchViewModel.searchRepository(emptySearchQuery)

        assert(stateObserver.valueCount() == 2)
        stateObserver.assertValueAt(0, SearchFragmentState.Loading)
        stateObserver.assertValueAt(
            1,
            SearchFragmentState.EmptyQuery
        )
    }

    @Test
    fun searchNoRepoFoundTest() {
        Mockito.`when`(useCase.searchRepository(correctSearchQuery))
            .thenReturn(Single.just(listOf()))
        val stateObserver = searchViewModel.getState().test()
        compositeDisposable.add(stateObserver)
        searchViewModel.searchRepository(correctSearchQuery)

        assert(stateObserver.valueCount() == 2)
        stateObserver.assertValueAt(0, SearchFragmentState.Loading)
        stateObserver.assertValueAt(
            1,
            SearchFragmentState.NoRepoFound
        )
    }

    @Test
    fun searchFailedTest() {
        Mockito.`when`(useCase.searchRepository(correctSearchQuery))
            .thenReturn(Single.error(Throwable()))
        val stateObserver = searchViewModel.getState().test()
        compositeDisposable.add(stateObserver)
        searchViewModel.searchRepository(correctSearchQuery)

        assert(stateObserver.valueCount() == 2)
        stateObserver.assertValueAt(0, SearchFragmentState.Loading)
        stateObserver.assertValueAt(
            1,
            SearchFragmentState.Error
        )
    }

    @After
    fun tearDown() {
        compositeDisposable.dispose()
    }
}
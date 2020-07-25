package com.mydigipay.challenge.presentation.github.presentaion.github.commit

import com.mydigipay.challenge.domain.model.Commit
import com.mydigipay.challenge.domain.model.CommitAuthor
import com.mydigipay.challenge.domain.model.mapToPresentationModel
import com.mydigipay.challenge.domain.usecase.CommitUseCase
import com.mydigipay.challenge.presentation.auth.AuthActivityState
import com.mydigipay.challenge.presentation.github.commit.CommitFragmentState
import com.mydigipay.challenge.presentation.github.commit.CommitViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CommitViewModelTest {
    private lateinit var commitViewModel: CommitViewModel
    val commitAuthor = CommitAuthor(
        name = "author",
        email = "email@myEmail.com",
        date = "date"
    )
    val commit = Commit(
        message = "commit message",
        author = commitAuthor,
        commentsCount = 2
    )

    @Mock
    private lateinit var useCase: CommitUseCase
    private val compositeDisposable = CompositeDisposable()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler {
            Schedulers.trampoline()
        }
        MockitoAnnotations.initMocks(this)
        commitViewModel = CommitViewModel(useCase)
    }

    @Test
    fun getCommitSuccessfully() {
        Mockito.`when`(useCase.getCommits(anyString(), anyString()))
            .thenReturn(Single.just(listOf(commit)))
        val stateObserver = commitViewModel.getState().test()
        commitViewModel.getCommits("", "")
        assert(stateObserver.valueCount() == 2)
        stateObserver.assertValueAt(0, CommitFragmentState.Loading)
        stateObserver.assertValueAt(
            1,
            CommitFragmentState.GotCommits(listOf(commit.mapToPresentationModel()))
        )
    }

    @Test
    fun getCommitFailed() {
        Mockito.`when`(useCase.getCommits(anyString(), anyString()))
            .thenReturn(Single.error(Throwable()))
        val stateObserver = commitViewModel.getState().test()
        commitViewModel.getCommits("", "")
        assert(stateObserver.valueCount() == 2)
        stateObserver.assertValueAt(0, CommitFragmentState.Loading)
        stateObserver.assertValueAt(
            1,
            CommitFragmentState.Error
        )
    }

    @Test
    fun getNoCommits() {
        Mockito.`when`(useCase.getCommits(anyString(), anyString()))
            .thenReturn(Single.just(listOf()))
        val stateObserver = commitViewModel.getState().test()
        commitViewModel.getCommits("", "")
        assert(stateObserver.valueCount() == 2)
        stateObserver.assertValueAt(0, CommitFragmentState.Loading)
        stateObserver.assertValueAt(
            1,
            CommitFragmentState.NoCommits
        )
    }

    @After
    fun tearDown() {
        compositeDisposable.dispose()
    }
}
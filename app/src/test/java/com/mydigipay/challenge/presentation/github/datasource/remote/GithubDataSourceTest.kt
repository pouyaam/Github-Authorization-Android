package com.mydigipay.challenge.presentation.github.datasource.remote

import com.mydigipay.challenge.data.datasource.api.ApiService
import com.mydigipay.challenge.data.datasource.remote.GithubDataSource
import com.mydigipay.challenge.data.model.commit.CommitResponseEntity
import com.mydigipay.challenge.data.model.commit.RemoteAuthorEntity
import com.mydigipay.challenge.data.model.commit.RemoteCommitEntity
import com.mydigipay.challenge.data.model.search.RemoteRepositoryEntity
import com.mydigipay.challenge.data.model.search.SearchResponse
import com.mydigipay.challenge.data.model.user.UserEntity
import com.mydigipay.challenge.datasource.remote.GithubDataSourceImpl
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GithubDataSourceTest {
    private lateinit var githubDataSource: GithubDataSource
    private val compositeDisposable = CompositeDisposable()
    private val searchQuery = "searchQuery"

    @Mock
    private lateinit var apiService: ApiService

    private val remoteRepositoryEntity = RemoteRepositoryEntity(
        id = 123456,
        name = "MyRepo",
        fullName = "Einhesari/MyRepo",
        isPrivate = false,
        defaultBranch = "master"
    )

    private val searchResponse = SearchResponse(
        totalCount = 1,
        isIncompleteResults = false,
        remoteSearchItemEntities = listOf(remoteRepositoryEntity)
    )

    private val userEntity = UserEntity(
        id = 123,
        name = "mohsen",
        avatarUrl = "myAvatarUrl",
        company = "MyCompany",
        bio = "myBio"
    )
    private val remoteAuthorEntity = RemoteAuthorEntity(
        date = "myDate",
        email = "email@myEmail.com",
        name = "myName"
    )
    private val remoteCommitEntity = RemoteCommitEntity(
        message = "myCommitMessage",
        author = remoteAuthorEntity,
        commentCount = 12
    )
    private val commitResponseEntity = CommitResponseEntity(
        commit = remoteCommitEntity
    )


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        githubDataSource = GithubDataSourceImpl(apiService)
    }


    @Test
    fun searchTest() {
        Mockito.`when`(apiService.performSearch(anyString()))
            .thenReturn(Single.just(searchResponse))
        val getSearchResultObserver = githubDataSource.search(searchQuery).test()
        compositeDisposable.add(getSearchResultObserver)
        getSearchResultObserver.assertComplete()
        getSearchResultObserver.assertNoErrors()
        getSearchResultObserver.assertValue {
            it.size == 1 &&
                    it.first().id == remoteRepositoryEntity.id &&
                    it.first().name.equals(remoteRepositoryEntity.name) &&
                    it.first().fullName.equals(remoteRepositoryEntity.fullName) &&
                    it.first().isPrivate == remoteRepositoryEntity.isPrivate &&
                    it.first().defaultBranch.equals(remoteRepositoryEntity.defaultBranch)
        }
    }

    @Test
    fun getUserTest() {
        Mockito.`when`(apiService.getUser())
            .thenReturn(Single.just(userEntity))
        val getUserObserver = githubDataSource.getUser().test()
        compositeDisposable.add(getUserObserver)
        getUserObserver.assertComplete()
        getUserObserver.assertNoErrors()
        getUserObserver.assertValue {
            it.name.equals(userEntity.name) &&
                    it.id == userEntity.id &&
                    it.avatarUrl.equals(userEntity.avatarUrl) &&
                    it.company.equals(userEntity.company) &&
                    it.bio.equals(userEntity.bio)
        }
    }

    @Test
    fun getCommitTest() {
        Mockito.`when`(apiService.getCommits(anyString(), anyString(), anyString()))
            .thenReturn(Single.just(listOf(commitResponseEntity)))
        val getCommitsObserver =
            githubDataSource.getCommits("", "").test()
        compositeDisposable.add(getCommitsObserver)
        getCommitsObserver.assertComplete()
        getCommitsObserver.assertNoErrors()
        getCommitsObserver.assertValue {
            it.size == 1 &&
                    it.first().author?.name.equals(commitResponseEntity.commit?.author?.name) &&
                    it.first().author?.email.equals(commitResponseEntity.commit?.author?.email) &&
                    it.first().author?.date.equals(commitResponseEntity.commit?.author?.date) &&
                    it.first().commentsCount == commitResponseEntity.commit?.commentCount &&
                    it.first().message.equals(commitResponseEntity.commit?.message)
        }
    }

    @After
    fun tearDown() {
        compositeDisposable.dispose()
    }
}
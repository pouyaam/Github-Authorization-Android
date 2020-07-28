package com.mydigipay.challenge.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.authorization.ReadAccessTokenUseCase
import com.mydigipay.challenge.model.Status
import com.mydigipay.challenge.presentation.design.MviViewModel
import com.mydigipay.challenge.presentation.mapper.GithubRepoListItemMapper
import com.mydigipay.challenge.presentation.model.ListItem
import com.mydigipay.challenge.presentation.viewstate.*
import com.mydigipay.challenge.repositories.GetGithubReposUseCase
import com.mydigipay.challenge.repositories.GithubRepo
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class GithubReposViewModel @Inject constructor(
    application: Application,
    private val getGithubReposUseCase: GetGithubReposUseCase,
    private val readAccessTokenUseCase: ReadAccessTokenUseCase,
    private val githubRepoListItemMapper: GithubRepoListItemMapper
) : MviViewModel<GithubReposViewState, GithubReposViewEffect, GithubReposViewEvent>(application) {

    init {
        viewState = GithubReposViewState(
            fetchStatus = GithubReposFetchStatus.InitialPageFetching,
            githubRepos = mutableListOf()
        )
    }

    override fun process(viewEvent: GithubReposViewEvent) {
        super.process(viewEvent)

        when (viewEvent) {
            is GithubReposViewEvent.GithubRepoClicked -> onGithubRepoClicked(viewEvent.githubRepoListItem)
            is GithubReposViewEvent.OnLoadMore -> retrieveGithubRepos(viewEvent.page)
            GithubReposViewEvent.OnSwipeRefresh -> retrieveGithubRepos(1)
        }
    }

    private fun onGithubRepoClicked(githubRepoListItem: ListItem.GithubRepoListItem) {
        viewEffect = GithubReposViewEffect.NavigateToRepoCommits(githubRepoListItem)
    }

    private fun retrieveGithubRepos(page: Int) {
        viewState = viewState.copy(fetchStatus = loadingStatusByPage(page), page = page)

        viewModelScope.launch {
            val result = getGithubReposUseCase(viewState.lastRepoId, readAccessToken())

            when (result.status) {
                Status.ERROR -> {
                    viewState = viewState.copy(
                        fetchStatus = errorStatusByPage(
                            page, result.error?.message ?: "Un-known API call error"
                        )
                    )
                }
                Status.SUCCESS -> {
                    viewState = viewState.copy(
                        fetchStatus = successStatusByPage(page),
                        githubRepos = totalGithubRepos(result.data as List<GithubRepo>),
                        lastRepoId = lastRepoId(result.data as List<GithubRepo>)
                    )
                }
                else -> throw IllegalArgumentException("Un-expected API call status")
            }
        }
    }

    private suspend fun readAccessToken(): String {
        return readAccessTokenUseCase().data!!
    }

    private fun loadingStatusByPage(page: Int) =
        if (page == 1) GithubReposFetchStatus.InitialPageFetching
        else GithubReposFetchStatus.SequencePageFetching

    private fun successStatusByPage(page: Int) =
        if (page == 1) GithubReposFetchStatus.InitialPageFetched
        else GithubReposFetchStatus.SequencePageFetched

    private fun errorStatusByPage(page: Int, errorMessage: String) =
        if (page == 1) GithubReposFetchStatus.InitialPageNotFetched(
            errorMessageByPage(page, errorMessage)
        )
        else GithubReposFetchStatus.SequencePageNotFetched(errorMessageByPage(page, errorMessage))

    private fun totalGithubRepos(githubRepos: List<GithubRepo>): List<ListItem.GithubRepoListItem> {
        return viewState.githubRepos + githubRepoListItemMapper.transformToEntities(githubRepos)
    }

    private fun lastRepoId(githubRepos: List<GithubRepo>) =
        if (githubRepos.isEmpty()) 0 else githubRepos.last().id

    /**
     * Add some magics here to make error messages more user-friendly. 😊
     */
    private fun errorMessageByPage(page: Int, errorMessage: String): String {
        return when (page) {
            1 -> "Unable to load any repository! Please check your internet and then swipe to refresh."
            else -> "Unable to load more repositories! Tap to retry."
        }
    }
}

package com.mydigipay.challenge.presentation.viewstate

import com.mydigipay.challenge.presentation.model.ListItem

data class GithubReposViewState(
    val fetchStatus: GithubReposFetchStatus,
    val githubRepos: List<ListItem.GithubRepoListItem>,
    val page: Int = 1,
    val lastRepoId: Int = 0
)

sealed class GithubReposViewEffect {
    data class ShowToast(val message: String) : GithubReposViewEffect()
    data class ShowSnackBar(val message: String) : GithubReposViewEffect()
    data class ShowDialog(val message: String) : GithubReposViewEffect()
    data class NavigateToRepoCommits(val githubRepoListItem: ListItem.GithubRepoListItem) : GithubReposViewEffect()
}

sealed class GithubReposViewEvent {
    data class GithubRepoClicked(val githubRepoListItem: ListItem.GithubRepoListItem) : GithubReposViewEvent()
    data class OnLoadMore(val page: Int) : GithubReposViewEvent()
    object OnSwipeRefresh : GithubReposViewEvent()
}

sealed class GithubReposFetchStatus {
    object InitialPageFetching : GithubReposFetchStatus()
    object InitialPageFetched : GithubReposFetchStatus()
    data class InitialPageNotFetched(val errorMessage: String) : GithubReposFetchStatus()
    object SequencePageFetching : GithubReposFetchStatus()
    object SequencePageFetched : GithubReposFetchStatus()
    data class SequencePageNotFetched(val errorMessage: String) : GithubReposFetchStatus()
}

fun buildLoadingItem(page: Int): ListItem.LoadingListItem =
    ListItem.LoadingListItem(page)

fun buildErrorItem(errorMsg: String, page: Int): ListItem.ErrorListItem =
    ListItem.ErrorListItem(errorMsg, page)

package com.mydigipay.challenge.presentation.model

import com.mydigipay.challenge.repositories.GithubRepo

sealed class ListItem(val viewType: ListItemType) {

    data class GithubRepoListItem(val githubRepo: GithubRepo) : ListItem(ListItemType.GITHUB_REPO_ITEM)

    data class LoadingListItem(val page: Int) : ListItem(ListItemType.LOADING_ITEM)

    data class ErrorListItem(
        val errorMsg: String,
        val page: Int
    ) : ListItem(ListItemType.ERROR_ITEM)
}

package com.mydigipay.challenge.domain.model

import com.mydigipay.challenge.presentation.model.RepositoryItem

data class RemoteRepository(
    var id: Int?,
    var nodeId: String?,
    var name: String?,
    var fullName: String?,
    var remoteRepositoryOwner: RemoteRepositoryOwner?,
    var isPrivate: Boolean? = false,
    var htmlUrl: String?,
    var description: String?,
    var isFork: Boolean? = false,
    var url: String?,
    var createdAt: String?,
    var updatedAt: String?,
    var pushedAt: String?,
    var homepage: String?,
    var size: Int?,
    var stargazersCount: Int?,
    var watchersCount: Int?,
    var language: String?,
    var forksCount: Int?,
    var openIssuesCount: Int?,
    var masterBranch: String?,
    var defaultBranch: String?,
    var score: Double?
)

fun RemoteRepository.mapToPresentationModel(): RepositoryItem {
    return RepositoryItem(
        id = id ?: 0,
        nodeId = nodeId ?: "",
        name = name ?: "",
        fullName = fullName ?: "",
        repoOwnerItem = remoteRepositoryOwner?.mapToPresentationModel(),
        isPrivate = isPrivate ?: false,
        htmlUrl = htmlUrl ?: "",
        description = description ?: "",
        isFork = isFork ?: false,
        url = url ?: "",
        createdAt = createdAt ?: "",
        updatedAt = updatedAt ?: "",
        pushedAt = pushedAt ?: "",
        homepage = homepage ?: "",
        size = size ?: 0,
        stargazersCount = stargazersCount ?: 0,
        watchersCount = watchersCount ?: 0,
        language = language ?: "",
        forksCount = forksCount ?: 0,
        openIssuesCount = openIssuesCount ?: 0,
        masterBranch = masterBranch ?: "",
        defaultBranch = defaultBranch ?: "",
        score = score ?: 0.0
    )
}
package com.mydigipay.challenge.domain.model

import com.mydigipay.challenge.presentation.model.RepositoryItem

data class RemoteRepository(
    var id: Int? = 0,
    var nodeId: String? = null,
    var name: String? = null,
    var fullName: String? = null,
    var remoteRepositoryOwner: RemoteRepositoryOwner? = null,
    var isPrivate: Boolean? = false,
    var htmlUrl: String? = null,
    var description: String? = null,
    var isFork: Boolean? = false,
    var url: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var pushedAt: String? = null,
    var homepage: String? = null,
    var size: Int? = 0,
    var stargazersCount: Int? = 0,
    var watchersCount: Int? = 0,
    var language: String? = null,
    var forksCount: Int? = 0,
    var openIssuesCount: Int? = 0,
    var masterBranch: String? = null,
    var defaultBranch: String? = null,
    var score: Double? = 0.0
)

fun RemoteRepository.mapToPresentationModel(): RepositoryItem {
    return RepositoryItem(
        id = id,
        nodeId = nodeId,
        name = name,
        fullName = fullName,
        repoOwnerItem = remoteRepositoryOwner?.mapToPresentationModel(),
        isPrivate = isPrivate,
        htmlUrl = htmlUrl,
        description = description,
        isFork = isFork,
        url = url,
        createdAt = createdAt,
        updatedAt = updatedAt,
        pushedAt = pushedAt,
        homepage = homepage,
        size = size,
        stargazersCount = stargazersCount,
        watchersCount = watchersCount,
        language = language,
        forksCount = forksCount,
        openIssuesCount = openIssuesCount,
        masterBranch = masterBranch,
        defaultBranch = defaultBranch,
        score = score
    )
}
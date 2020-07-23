package com.mydigipay.challenge.data.model

import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.domain.model.RemoteRepository

data class RemoteRepositorySearchResponse(
    @SerializedName("id")
    var id: Int? = 0,

    @SerializedName("node_id")
    var nodeId: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("full_name")
    var fullName: String? = null,

    @SerializedName("owner")
    var remoteOwnerEntity: RemoteOwnerEntity? = null,

    @SerializedName("private")
    var isPrivate: Boolean? = false,

    @SerializedName("html_url")
    var htmlUrl: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("fork")
    var isFork: Boolean? = false,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("created_at")
    var createdAt: String? = null,

    @SerializedName("updated_at")
    var updatedAt: String? = null,

    @SerializedName("pushed_at")
    var pushedAt: String? = null,

    @SerializedName("homepage")
    var homepage: String? = null,

    @SerializedName("size")
    var size: Int? = null,

    @SerializedName("stargazers_count")
    var stargazersCount: Int? = 0,

    @SerializedName("watchers_count")
    var watchersCount: Int? = 0,

    @SerializedName("language")
    var language: String? = null,

    @SerializedName("forks_count")
    var forksCount: Int? = 0,

    @SerializedName("open_issues_count")
    var openIssuesCount: Int? = 0,

    @SerializedName("master_branch")
    var masterBranch: String? = null,

    @SerializedName("default_branch")
    var defaultBranch: String? = null,

    @SerializedName("score")
    var score: Double? = 0.0
)

fun RemoteRepositorySearchResponse.mapToDomainModel(): RemoteRepository {
    return RemoteRepository(
        id = id,
        nodeId = nodeId,
        name = name,
        fullName = fullName,
        remoteRepositoryOwner = remoteOwnerEntity?.mapToDomainModel(),
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
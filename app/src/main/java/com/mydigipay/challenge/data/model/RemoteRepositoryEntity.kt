package com.mydigipay.challenge.data.model

import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.domain.model.RemoteRepository

data class RemoteRepositorySearchResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("node_id")
    val nodeId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("full_name")
    val fullName: String,

    @SerializedName("owner")
    val remoteOwnerEntity: RemoteOwnerEntity,

    @SerializedName("private")
    val isPrivate: Boolean,

    @SerializedName("html_url")
    val htmlUrl: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("fork")
    val isFork: Boolean,

    @SerializedName("url")
    val url: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("pushed_at")
    val pushedAt: String,

    @SerializedName("homepage")
    val homepage: String,

    @SerializedName("size")
    val size: Int,

    @SerializedName("stargazers_count")
    val stargazersCount: Int,

    @SerializedName("watchers_count")
    val watchersCount: Int,

    @SerializedName("language")
    val language: String,

    @SerializedName("forks_count")
    val forksCount: Int,

    @SerializedName("open_issues_count")
    val openIssuesCount: Int,

    @SerializedName("master_branch")
    val masterBranch: String,

    @SerializedName("default_branch")
    val defaultBranch: String,

    @SerializedName("score")
    val score: Double
)

fun RemoteRepositorySearchResponse.mapToDomainModel(): RemoteRepository {
    return RemoteRepository(
        id = id,
        nodeId = nodeId,
        name = name,
        fullName = fullName,
        remoteRepositoryOwner = remoteOwnerEntity.mapToDomainModel(),
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
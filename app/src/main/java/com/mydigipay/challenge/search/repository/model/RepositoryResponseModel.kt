package com.mydigipay.challenge.search.repository.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponseModel(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("incomplete_results")
    val incompleteResult: Boolean,

    val items: List<RepositoryModel>
)

data class RepositoryModel(
    val id: Long,
    val name: String,

    @SerializedName("full_name")
    val fullName: String?,

    val description: String?,
    val url: String,
    val owner: Owner,

    @SerializedName("commits_url")
    val commitsUrl: String,

    @SerializedName("stargazers_count")
    val starCount: Int,

    @SerializedName("watchers_count")
    val watchersCount: Int,

    val language: String?
)

data class Owner(
    val login: String,
    val id: Long,

    @SerializedName("avatar_url")
    val avatarUrl: String?
)
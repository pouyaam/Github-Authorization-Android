package com.mydigipay.challenge.commit.repository.model

import com.google.gson.annotations.SerializedName

data class CommitResponseModel(
    val commit: CommitModel,
    val author: AuthorModel?
)

data class CommitModel(
    val message: String?,
    val commentCount: Int,
    val author: CommitAuthorModel
)

data class CommitAuthorModel(
    val date: String
)

data class AuthorModel(
    val login: String?,
    val id: Long,

    @SerializedName("avatar_url")
    val avatarUrl: String
)
package com.mydigipay.challenge.profile.repository.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    val login: String,
    val id: Long,

    @SerializedName("avatar_url")
    val avatarUrl: String?,

    @SerializedName("organizations_url")
    val organizationUrl: String?,

    val name: String?,
    val company: String?,
    val location: String?,
    val email: String?,
    val bio: String?,

    @SerializedName("public_repos")
    val repositories: Int,

    val followers: Int,
    val following: Int
)
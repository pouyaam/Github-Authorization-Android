package com.mydigipay.challenge.network.model.user

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val login: String ,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("company")
    val company: String? = null,
    @SerializedName("blog")
    val blog: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("bio")
    val bio: String? = null,
    @SerializedName("public_repos")
    val publicRepos: Int? = null,
    @SerializedName("public_gists")
    val publicGists: Int? = null,
    @SerializedName("followers")
    val followers: Int? = null,
    @SerializedName("following")
    val following: Int? = null,
    @SerializedName("private_gists")
    val privateGists: Int? = null,
    @SerializedName("total_private_repos")
    val totalPrivateRepos: Int? = null,
    @SerializedName("owned_private_repos")
    val ownedPrivateRepos: Int? = null,
    @SerializedName("disk_usage")
    val diskUsage: Int? = null,
    @SerializedName("collaborators")
    val collaborators: Int? = null

) {
    companion object {
        val EMPTY = User("")
    }
}
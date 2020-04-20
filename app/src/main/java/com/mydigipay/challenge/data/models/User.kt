package com.mydigipay.challenge.data.models

import com.google.gson.annotations.SerializedName

data class User(
    val login: String,
    var id: Int? = null,
    @SerializedName("avatar_url")
    var avatarUrl: String? = null,
    var bio: String? = null,
    var blog: String? = null,
    var collaborators: Int? = null,
    var company: String? = null,
    @SerializedName("created_at")
    var createdAt: String? = null,
    @SerializedName("disk_usage")
    var diskUsage: Int? = null,
    var email: String? = null,
    @SerializedName("events_url")
    var eventsUrl: String? = null,
    var followers: Int? = null,
    @SerializedName("followers_url")
    var followersUrl: String? = null,
    var following: Int? = null,
    @SerializedName("following_url")
    var followingUrl: String? = null,
    @SerializedName("gists_url")
    var gistsUrl: String? = null,
    @SerializedName("gravatar_id")
    var gravatarId: String? = null,
    var hireable: Boolean? = null,
    @SerializedName("html_url")
    var htmlUrl: String? = null,
    var location: String? = null,
    var name: String? = null,
    @SerializedName("node_id")
    var nodeId: String? = null,
    @SerializedName("organizations_url")
    var organizationsUrl: String? = null,
    @SerializedName("owned_private_repos")
    var ownedPrivateRepos: Int? = null,
    @SerializedName("private_gists")
    var privateGists: Int? = null,
    @SerializedName("public_gists")
    var publicGists: Int? = null,
    @SerializedName("public_repos")
    var publicRepos: Int? = null,
    @SerializedName("received_events_url")
    var receivedEventsUrl: String? = null,
    @SerializedName("repos_url")
    var reposUrl: String? = null,
    @SerializedName("site_admin")
    var siteAdmin: Boolean? = null,
    @SerializedName("starred_url")
    var starredUrl: String? = null,
    @SerializedName("subscriptions_url")
    var subscriptionsUrl: String? = null,
    @SerializedName("total_private_repos")
    var totalPrivateRepos: Int? = null,
    @SerializedName("two_factor_authentication")
    var twoFactorAuthentication: Boolean? = null,
    var url: String? = null
)

package com.mydigipay.challenge.data.model.search

import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.domain.model.User

data class UserEntity(
    @SerializedName("login") var login: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("node_id") var nodeId: String? = null,
    @SerializedName("avatar_url") var avatarUrl: String? = null,
    @SerializedName("gravatar_id") var gravatarId: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("html_url") var htmlUrl: String? = null,
    @SerializedName("followers_url") var followersUrl: String? = null,
    @SerializedName("following_url") var followingUrl: String? = null,
    @SerializedName("gists_url") var gistsUrl: String? = null,
    @SerializedName("starred_url") var starredUrl: String? = null,
    @SerializedName("subscriptions_url") var subscriptionsUrl: String? = null,
    @SerializedName("organizations_url") var organizationsUrl: String? = null,
    @SerializedName("repos_url") var reposUrl: String? = null,
    @SerializedName("events_url") var eventsUrl: String? = null,
    @SerializedName("received_events_url") var receivedEventsUrl: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("site_admin") var site_admin: Boolean? = false,
    @SerializedName("name") var name: String? = null,
    @SerializedName("company") var company: String? = null,
    @SerializedName("blog") var blog: String? = null,
    @SerializedName("location") var location: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("hireable") var hireable: Boolean? = false,
    @SerializedName("bio") var bio: String? = null,
    @SerializedName("twitter_username") var twitterUsername: String? = null,
    @SerializedName("public_repos") var publicRepos: String? = null,
    @SerializedName("public_gists") var publicGists: String? = null,
    @SerializedName("followers") var followers: String? = null,
    @SerializedName("following") var following: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)

fun UserEntity.mapToDomainModel(): User {
    return User(
        login = login,
        id = id,
        nodeId = nodeId,
        avatarUrl = avatarUrl,
        gravatarId = gravatarId,
        url = url,
        htmlUrl = htmlUrl,
        followersUrl = followersUrl,
        followingUrl = followingUrl,
        gistsUrl = gistsUrl,
        starredUrl = starredUrl,
        subscriptionsUrl = subscriptionsUrl,
        organizationsUrl = organizationsUrl,
        reposUrl = reposUrl,
        eventsUrl = eventsUrl,
        receivedEventsUrl = receivedEventsUrl,
        type = type,
        site_admin = site_admin,
        name = name,
        company = company,
        blog = blog,
        location = location,
        email = email,
        hireable = hireable,
        bio = bio,
        twitterUsername = twitterUsername,
        publicRepos = publicRepos,
        publicGists = publicGists,
        followers = followers,
        following = following,
        createdAt = createdAt,
        updatedAt = updatedAt

    )
}


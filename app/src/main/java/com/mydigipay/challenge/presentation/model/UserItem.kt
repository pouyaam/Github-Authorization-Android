package com.mydigipay.challenge.presentation.model

data class UserItem(
    var login: String,
    var id: String,
    var nodeId: String,
    var avatarUrl: String,
    var gravatarId: String,
    var url: String,
    var htmlUrl: String,
    var followersUrl: String,
    var followingUrl: String,
    var gistsUrl: String,
    var starredUrl: String,
    var subscriptionsUrl: String,
    var organizationsUrl: String,
    var reposUrl: String,
    var eventsUrl: String,
    var receivedEventsUrl: String,
    var type: String,
    var site_admin: Boolean = false,
    var name: String,
    var company: String,
    var blog: String,
    var location: String,
    var email: String,
    var hireable: Boolean = false,
    var bio: String,
    var twitterUsername: String,
    var publicRepos: String,
    var publicGists: String,
    var followers: String,
    var following: String,
    var createdAt: String,
    var updatedAt: String
)
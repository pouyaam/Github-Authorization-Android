package com.mydigipay.challenge.domain.model

data class RemoteRepositoryOwner(
    val login: String,
    val id: Int,
    val nodeId: String,
    val avatarUrl: String,
    val gravatarId: String,
    val url: String,
    val receivedEventsUrl: String,
    val type: String
)
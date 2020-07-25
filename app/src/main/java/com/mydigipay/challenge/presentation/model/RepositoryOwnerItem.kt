package com.mydigipay.challenge.presentation.model

data class RepositoryOwnerItem(
    var login: String,
    var id: Int,
    var nodeId: String,
    var avatarUrl: String,
    var gravatarId: String,
    var url: String,
    var receivedEventsUrl: String,
    var type: String
)
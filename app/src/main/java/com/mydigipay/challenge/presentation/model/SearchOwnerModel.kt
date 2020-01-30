package com.mydigipay.challenge.presentation.model

data class SearchOwnerModel(
    var login: String? = null,
    var id: Int = 0,
    var nodeId: String? = null,
    var avatarUrl: String? = null,
    var gravatarId: String? = null,
    var url: String? = null,
    var receivedEventsUrl: String? = null,
    var type: String? = null
)
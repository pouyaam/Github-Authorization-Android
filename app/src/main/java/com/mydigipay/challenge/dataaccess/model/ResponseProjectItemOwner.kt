package com.mydigipay.challenge.dataaccess.model

import com.google.gson.annotations.SerializedName

data class ResponseProjectItemOwner(
    @SerializedName("login")
    var login: String? = null,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("node_id")
    var nodeId: String? = null,
    @SerializedName("avatar_url")
    var avatarUrl: String? = null,
    @SerializedName("gravatar_id")
    var gravatarId: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("received_events_url")
    var receivedEventsUrl: String? = null,
    @SerializedName("type")
    var type: String? = null
)
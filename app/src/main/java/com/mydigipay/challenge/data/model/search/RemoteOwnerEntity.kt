package com.mydigipay.challenge.data.model.search

import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.domain.model.RemoteRepositoryOwner

data class RemoteOwnerEntity(
    @SerializedName("login")
    val login: String? = null,

    @SerializedName("id")
    val id: Int? = 0,

    @SerializedName("node_id")
    val nodeId: String? = null,

    @SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @SerializedName("gravatar_id")
    val gravatarId: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("received_events_url")
    val receivedEventsUrl: String? = null,

    @SerializedName("type")
    val type: String? = null
)

fun RemoteOwnerEntity.mapToDomainModel(): RemoteRepositoryOwner {
    return RemoteRepositoryOwner(
        login = login,
        id = id,
        nodeId = nodeId,
        avatarUrl = avatarUrl,
        gravatarId = gravatarId,
        url = url,
        receivedEventsUrl = receivedEventsUrl,
        type = type
    )
}
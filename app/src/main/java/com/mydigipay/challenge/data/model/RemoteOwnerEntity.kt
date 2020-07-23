package com.mydigipay.challenge.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mydigipay.challenge.domain.model.RemoteRepositoryOwner

data class RemoteOwnerEntity(
    @SerializedName("login")
    val login: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("node_id")
    val nodeId: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("gravatar_id")
    val gravatarId: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("received_events_url")
    val receivedEventsUrl: String,

    @SerializedName("type")
    val type: String
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
package com.mydigipay.challenge.domain.model

import com.mydigipay.challenge.presentation.model.RepositoryOwnerItem

data class RemoteRepositoryOwner(
    var login: String? ,
    var id: Int?,
    var nodeId: String? ,
    var avatarUrl: String? ,
    var gravatarId: String? ,
    var url: String? ,
    var receivedEventsUrl: String? ,
    var type: String? 
)

fun RemoteRepositoryOwner.mapToPresentationModel(): RepositoryOwnerItem {
    return RepositoryOwnerItem(
        login = login ?: "N/A",
        id = id ?: 0,
        nodeId = nodeId ?: "N/A",
        avatarUrl = avatarUrl?: "N/A",
        gravatarId = gravatarId?: "N/A",
        url = url?: "N/A",
        receivedEventsUrl = receivedEventsUrl?: "N/A",
        type = type?: "N/A"
    )
}
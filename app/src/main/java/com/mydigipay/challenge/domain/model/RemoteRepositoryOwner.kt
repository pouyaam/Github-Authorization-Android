package com.mydigipay.challenge.domain.model

import com.mydigipay.challenge.presentation.model.RepositoryOwnerItem

data class RemoteRepositoryOwner(
    var login: String? = null,
    var id: Int? = 0,
    var nodeId: String? = null,
    var avatarUrl: String? = null,
    var gravatarId: String? = null,
    var url: String? = null,
    var receivedEventsUrl: String? = null,
    var type: String? = null
)

fun RemoteRepositoryOwner.mapToPresentationModel(): RepositoryOwnerItem {
    return RepositoryOwnerItem(
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
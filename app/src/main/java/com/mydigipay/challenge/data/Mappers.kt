package com.mydigipay.challenge.data

import com.mydigipay.challenge.data.api.github.RemoteItemEntity
import com.mydigipay.challenge.data.api.github.RemoteOwnerEntity
import com.mydigipay.challenge.data.api.token.ResponseAccessToken
import com.mydigipay.challenge.domain.entities.AccessTokenEntity
import com.mydigipay.challenge.domain.entities.ItemEntity
import com.mydigipay.challenge.domain.entities.OwnerEntity

fun ResponseAccessToken.toAccessTokenEntity() =
    AccessTokenEntity(
        accessToken,
        tokenType
    )

fun RemoteOwnerEntity.toOwnerEntity() = OwnerEntity(
    login, id, nodeId, avatarUrl, gravatarId, url, receivedEventsUrl, type
)

fun RemoteItemEntity.toItemEntity() = ItemEntity(
    id, nodeId, name, fullName
)
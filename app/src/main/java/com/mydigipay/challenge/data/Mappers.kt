package com.mydigipay.challenge.data

import com.mydigipay.challenge.data.api.github.RemoteOwnerEntity
import com.mydigipay.challenge.data.api.github.RemoteSearchItemEntity
import com.mydigipay.challenge.data.api.token.ResponseAccessToken
import com.mydigipay.challenge.domain.entities.AccessTokenEntity
import com.mydigipay.challenge.domain.entities.SearchItemEntity
import com.mydigipay.challenge.domain.entities.SearchOwnerEntity

fun ResponseAccessToken.toAccessTokenEntity() =
    AccessTokenEntity(
        accessToken,
        tokenType
    )

fun RemoteOwnerEntity.toSearchOwnerEntity() = SearchOwnerEntity(
    login, id, nodeId, avatarUrl, gravatarId, url, receivedEventsUrl, type
)

fun RemoteSearchItemEntity.toSearchItemEntity() = SearchItemEntity(
    id,
    nodeId,
    name,
    fullName,
    remoteOwnerEntity?.toSearchOwnerEntity(),
    isPrivate,
    htmlUrl,
    description,
    isFork,
    url,
    createdAt,
    updatedAt,
    pushedAt,
    homepage,
    size,
    stargazersCount,
    watchersCount,
    language,
    forksCount,
    openIssuesCount,
    masterBranch,
    defaultBranch,
    score
)
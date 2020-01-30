package com.mydigipay.challenge.presentation.model

import com.mydigipay.challenge.domain.entities.AccessTokenEntity
import com.mydigipay.challenge.domain.entities.SearchItemEntity
import com.mydigipay.challenge.domain.entities.SearchOwnerEntity

fun AccessTokenEntity.toAccessTokenModel() =
    AccessTokenModel(
        accessToken,
        tokenType
    )

fun SearchItemEntity.toSearchItemModel() = SearchItemModel(
    id,
    nodeId,
    name,
    fullName,
    searchOwnerEntity?.toSearchOwnerModel(),
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

fun SearchOwnerEntity.toSearchOwnerModel() =
    SearchOwnerModel(login, id, nodeId, avatarUrl, gravatarId, url, receivedEventsUrl, type)
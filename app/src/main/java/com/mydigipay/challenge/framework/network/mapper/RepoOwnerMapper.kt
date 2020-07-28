package com.mydigipay.challenge.framework.network.mapper

import com.mydigipay.challenge.framework.network.response.RepoOwnerResponse
import com.mydigipay.challenge.mapper.DataMapper
import com.mydigipay.challenge.repositories.RepoOwner
import javax.inject.Inject

class RepoOwnerMapper @Inject constructor() : DataMapper<RepoOwner, RepoOwnerResponse>() {

    override fun transformToEntity(model: RepoOwner): RepoOwnerResponse? {
        // Unnecessary transform
        return null
    }

    override fun transformToModel(entity: RepoOwnerResponse): RepoOwner? {
        return RepoOwner(
            entity.login,
            entity.id,
            entity.nodeId,
            entity.avatarUrl,
            entity.grAvatarId,
            entity.url,
            entity.htmlUrl,
            entity.followersUrl,
            entity.followingUrl,
            entity.gistsUrl,
            entity.starredUrl,
            entity.subscriptionsUrl,
            entity.organizationsUrl,
            entity.reposUrl,
            entity.eventsUrl,
            entity.receivedEventsUrl,
            entity.type,
            entity.siteAdmin
        )
    }
}

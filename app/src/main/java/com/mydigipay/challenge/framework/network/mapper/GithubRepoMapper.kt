package com.mydigipay.challenge.framework.network.mapper

import com.mydigipay.challenge.framework.network.response.GithubRepoResponse
import com.mydigipay.challenge.mapper.DataMapper
import com.mydigipay.challenge.repositories.GithubRepo
import javax.inject.Inject

class GithubRepoMapper @Inject constructor(
    private val repoOwnerMapper: RepoOwnerMapper
) : DataMapper<GithubRepo, GithubRepoResponse>() {

    override fun transformToEntity(model: GithubRepo): GithubRepoResponse? {
        // Unnecessary transform
        return null
    }

    override fun transformToModel(entity: GithubRepoResponse): GithubRepo? {
        return GithubRepo(
            entity.id,
            entity.nodeId,
            entity.name,
            entity.fullName,
            entity.`private`,
            repoOwnerMapper.transformToModel(entity.repoOwnerResponse)!!,
            entity.htmlUrl,
            entity.description,
            entity.fork,
            entity.url,
            entity.forksUrl,
            entity.keysUrl,
            entity.collaboratorsUrl,
            entity.teamsUrl,
            entity.hooksUrl,
            entity.issueEventsUrl,
            entity.eventsUrl,
            entity.assigneesUrl,
            entity.branchesUrl,
            entity.tagsUrl,
            entity.blobsUrl,
            entity.gitTagsUrl,
            entity.gitRefsUrl,
            entity.treesUrl,
            entity.statusesUrl,
            entity.languagesUrl,
            entity.stargazersUrl,
            entity.contributorsUrl,
            entity.subscribersUrl,
            entity.subscriptionUrl,
            entity.commitsUrl,
            entity.gitCommitsUrl,
            entity.commentsUrl,
            entity.issueCommentUrl,
            entity.contentsUrl,
            entity.compareUrl,
            entity.mergesUrl,
            entity.archiveUrl,
            entity.downloadsUrl,
            entity.issuesUrl,
            entity.pullsUrl,
            entity.milestonesUrl,
            entity.notificationsUrl,
            entity.labelsUrl,
            entity.releasesUrl,
            entity.deploymentsUrl
        )
    }
}

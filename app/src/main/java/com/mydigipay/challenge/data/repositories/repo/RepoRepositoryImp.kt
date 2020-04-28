package com.mydigipay.challenge.data.repositories.repo

import com.mydigipay.challenge.data.models.Branch
import com.mydigipay.challenge.data.models.Commit
import com.mydigipay.challenge.data.models.Repo
import com.mydigipay.challenge.data.network.services.RepoService
import com.mydigipay.challenge.data.repositories.ApiResult
import com.mydigipay.challenge.data.repositories.fetchFromNetwork

class RepoRepositoryImp(
    private val repoService: RepoService
) : RepoRepository {
    override suspend fun getBranches(repo: Repo): ApiResult<List<Branch>> =
        fetchFromNetwork({ repoService.getBranches(repo.owner.login, repo.name) })

    override suspend fun getCommits(
        repo: Repo,
        branch: String,
        page: Int
    ): ApiResult<List<Commit>> =
        fetchFromNetwork({ repoService.getCommits(repo.owner.login, repo.name, branch) })
}
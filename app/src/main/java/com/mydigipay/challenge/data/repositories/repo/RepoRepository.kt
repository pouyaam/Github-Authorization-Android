package com.mydigipay.challenge.data.repositories.repo

import com.mydigipay.challenge.data.models.Branch
import com.mydigipay.challenge.data.models.CommitDetail
import com.mydigipay.challenge.data.models.Repo
import com.mydigipay.challenge.data.repositories.ApiResult

interface RepoRepository {

    suspend fun getBranches(repo: Repo): ApiResult<List<Branch>>
    suspend fun getCommits(repo: Repo, branch: String, page: Int): ApiResult<List<CommitDetail>>
}
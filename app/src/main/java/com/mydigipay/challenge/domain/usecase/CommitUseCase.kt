package com.mydigipay.challenge.domain.usecase

import com.mydigipay.challenge.data.datasource.remote.GithubDataSource
import com.mydigipay.challenge.domain.model.Commit
import io.reactivex.Single
import javax.inject.Inject

class CommitUseCase @Inject constructor(private val githubDataSource: GithubDataSource) {

    fun getCommits(owner: String, repo: String): Single<List<Commit>> {
        return githubDataSource.getCommits(owner, repo)
    }
}
package com.mydigipay.challenge.repositories

import com.mydigipay.challenge.model.Resource
import javax.inject.Inject

class GithubRepoRepository @Inject constructor(private val dataSource: GithubRepoDataSource) {

    suspend fun getGithubRepos(since: Int, token: String): Resource<List<GithubRepo>> =
        dataSource.getGithubRepos(since, token)
}

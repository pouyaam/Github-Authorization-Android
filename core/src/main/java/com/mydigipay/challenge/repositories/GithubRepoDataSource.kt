package com.mydigipay.challenge.repositories

import com.mydigipay.challenge.model.Resource

interface GithubRepoDataSource {

    suspend fun getGithubRepos(since: Int, token: String): Resource<List<GithubRepo>>
}

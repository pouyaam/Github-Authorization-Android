package com.mydigipay.challenge.ui.home

import com.mydigipay.challenge.network.oauth.GithubApiService


class HomeRepositoryImpl(val githubApiService: GithubApiService) : HomeRepository {

    override suspend fun getRepositories() =
        githubApiService.getRepositories().await()

}
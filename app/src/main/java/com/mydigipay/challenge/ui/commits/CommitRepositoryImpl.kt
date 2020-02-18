package com.mydigipay.challenge.ui.commits

import android.content.SharedPreferences
import com.mydigipay.challenge.network.oauth.GithubApiService
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException

class CommitRepositoryImpl(private val githubApiService: GithubApiService) : CommitRepository {

    override suspend fun getCommits(owner: String, repo: String) =
        githubApiService.getCommits(owner, repo).await()

}
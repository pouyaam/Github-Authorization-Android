package com.mydigipay.challenge.repositories

import com.mydigipay.challenge.model.Resource
import javax.inject.Inject

class GetGithubReposUseCase @Inject constructor(private val repository: GithubRepoRepository) {

    suspend operator fun invoke(since: Int, token: String): Resource<List<GithubRepo>> =
        repository.getGithubRepos(since, token)
}

package com.mydigipay.challenge.domain.interactors

import com.github.mohammadsianaki.core.model.Result
import com.mydigipay.challenge.domain.entities.CommitEntity
import com.mydigipay.challenge.domain.repositories.github.GithubRepository
import com.mydigipay.challenge.presentation.model.RepoFullNameModel
import com.mydigipay.challenge.presentation.ui.github.detail.DetailViewModel.Companion.KEY_ARG


class GetCommitsUsecase(private val githubRepository: GithubRepository) :
    Usecase<Result<CommitEntity>>() {
    override suspend fun execute(data: Map<String, Any>?): Result<CommitEntity> {
        val fullName = data?.let { it[KEY_ARG] } as RepoFullNameModel
        return githubRepository.getCommitsForRepo(fullName.owner, fullName.repo)
    }
}
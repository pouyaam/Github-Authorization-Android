package com.mydigipay.challenge.ui.search.detail

import com.mydigipay.challenge.base.BaseModel
import com.mydigipay.challenge.data.repository.CommitRepository

class DetailModel(private val commitRepository: CommitRepository) : BaseModel() {

    suspend fun getCommits(
        owner: String,
        repo: String,
        page: Int
    ) = commitRepository.getCommitsRepository(owner, repo, page)
}
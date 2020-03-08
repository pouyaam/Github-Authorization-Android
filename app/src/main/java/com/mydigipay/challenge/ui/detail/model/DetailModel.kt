package com.mydigipay.challenge.ui.detail.model

import com.mydigipay.challenge.dataaccess.CommitDataSource

class DetailModel(
    private val repository: CommitDataSource
) {
    fun getAll(owner: String, repo: String) = repository.getAll(owner, repo)
}
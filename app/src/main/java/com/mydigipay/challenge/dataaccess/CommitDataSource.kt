package com.mydigipay.challenge.dataaccess

import com.mydigipay.challenge.dataaccess.model.ResponseCommitItem
import io.reactivex.Single

interface CommitDataSource {

    fun getAll(owner: String, repo: String): Single<List<ResponseCommitItem>>

}
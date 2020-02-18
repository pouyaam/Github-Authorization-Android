package com.mydigipay.challenge.ui.commits

import com.mydigipay.challenge.base.Authorizer
import com.mydigipay.challenge.network.model.commit.CommitWrapper

interface CommitRepository:Authorizer {
    suspend fun getCommits(owner: String,repo: String):  List<CommitWrapper>
}
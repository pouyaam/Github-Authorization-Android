package com.mydigipay.challenge.domain.repository

import com.mydigipay.challenge.domain.model.RemoteRepository
import com.mydigipay.challenge.domain.model.User
import io.reactivex.Single

interface GithubRepository {
    fun search(query: String): Single<List<RemoteRepository>>
    fun getUser() : Single<User>
}
package com.mydigipay.challenge.domain.usecase

import com.mydigipay.challenge.domain.model.User
import com.mydigipay.challenge.domain.repository.GithubRepository
import io.reactivex.Single
import javax.inject.Inject

class UserUseCase @Inject constructor(private val githubRepository: GithubRepository) {
    fun getUser(): Single<User> {
        return githubRepository.getUser()
    }
}
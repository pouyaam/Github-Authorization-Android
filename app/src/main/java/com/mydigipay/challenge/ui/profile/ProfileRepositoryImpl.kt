package com.mydigipay.challenge.ui.profile

import com.mydigipay.challenge.network.model.user.User
import com.mydigipay.challenge.network.oauth.GithubApiService

class ProfileRepositoryImpl(val githubApiService: GithubApiService) : ProfileRepository {

    override suspend fun getProfile(): User =
        githubApiService.getUserProfile().await()


    override suspend fun updateUserProfile(userInfo: Map<String, Any>) =
        githubApiService.updateUserProfile(userInfo).await()
}
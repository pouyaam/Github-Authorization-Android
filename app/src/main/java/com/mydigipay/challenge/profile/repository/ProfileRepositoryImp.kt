package com.mydigipay.challenge.profile.repository

import com.mydigipay.challenge.profile.repository.remote.ProfileService

class ProfileRepositoryImp(private val profileService: ProfileService) : ProfileRepository {

    override suspend fun getOtherUserInfo(username: String) =
        profileService.getOtherUserInfo(username)

    override suspend fun getUserInfo(token: String) = profileService.getUserInfo(token)
}
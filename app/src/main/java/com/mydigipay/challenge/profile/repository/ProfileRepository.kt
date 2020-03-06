package com.mydigipay.challenge.profile.repository

import com.mydigipay.challenge.core.network.RetrofitResult
import com.mydigipay.challenge.profile.repository.model.UserModel

interface ProfileRepository {
    suspend fun getOtherUserInfo(username: String): RetrofitResult<UserModel>

    suspend fun getUserInfo(token:String): RetrofitResult<UserModel>
}
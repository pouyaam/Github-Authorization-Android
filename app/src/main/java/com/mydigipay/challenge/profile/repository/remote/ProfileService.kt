package com.mydigipay.challenge.profile.repository.remote

import com.mydigipay.challenge.core.network.RetrofitResult
import com.mydigipay.challenge.profile.repository.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ProfileService {
    @GET("/users/{username}")
    suspend fun getOtherUserInfo(@Path("username") username: String): RetrofitResult<UserModel>

    @GET("/user")
    suspend fun getUserInfo(@Header("Authorization") token: String): RetrofitResult<UserModel>
}
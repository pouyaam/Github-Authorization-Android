package com.mydigipay.challenge.ui.profile

import com.mydigipay.challenge.network.model.user.User

interface ProfileRepository  {
    suspend fun getProfile(): User
    suspend fun updateUserProfile(userInfo: Map<String, String>): User
}
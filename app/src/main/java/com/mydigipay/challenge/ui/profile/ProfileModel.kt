package com.mydigipay.challenge.ui.profile

import com.mydigipay.challenge.base.BaseModel
import com.mydigipay.challenge.data.repository.UserRepository

class ProfileModel(private val userRepository: UserRepository) : BaseModel() {

    suspend fun getAuthenticatedUser() = userRepository.getAuthenticatedUser()
}
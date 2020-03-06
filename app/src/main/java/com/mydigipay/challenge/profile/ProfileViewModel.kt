package com.mydigipay.challenge.profile

import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.core.SingleLiveEvent
import com.mydigipay.challenge.core.base.BaseViewModel
import com.mydigipay.challenge.core.network.RetrofitResult
import com.mydigipay.challenge.profile.repository.ProfileRepository
import com.mydigipay.challenge.profile.repository.model.UserModel
import kotlinx.coroutines.launch

class ProfileViewModel(private val profileRepository: ProfileRepository) : BaseViewModel() {
    val userInfo = SingleLiveEvent<UserModel>()
    private var isLoading = false

    fun getUserInfo(username: String?, userType: String, token: String) {
        if (userInfo.value == null && !isLoading) {
            showProgressBar.call()
            isLoading = true

            viewModelScope.launch {
                val result = when (userType) {
                    UserType.AuthenticatedUser.name -> {
                        profileRepository.getUserInfo(token)
                    }

                    UserType.OtherUser.name -> {
                        profileRepository.getOtherUserInfo(username ?: "")
                    }

                    else -> {
                        profileRepository.getUserInfo(token)
                    }
                }
                hideProgressBar.call()

                if (result is RetrofitResult.Success) {
                    userInfo.value = result.data
                } else if (result is RetrofitResult.Error) {
                    mError.value = result.e.message
                }

                isLoading = false
            }
        }
    }
}

enum class UserType {
    AuthenticatedUser,
    OtherUser
}
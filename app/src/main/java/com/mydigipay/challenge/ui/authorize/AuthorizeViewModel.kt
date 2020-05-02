package com.mydigipay.challenge.ui.authorize

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.mydigipay.challenge.R
import com.mydigipay.challenge.auth.AuthenticationUtil
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.models.User
import com.mydigipay.challenge.data.repositories.ApiResult
import com.mydigipay.challenge.data.repositories.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val CLIENT_ID = "bbefe4b0d35d6f211fa3"
const val CLIENT_SECRET = "4beac0e08bcd36a8e307e987702d260544d59906"
const val REDIRECT_URI = "challenge://account/authorize"
const val TOKEN_BEARER = "Bearer"
const val STATE = "895623124578"

class AuthorizeViewModel(
    private val userRepository: UserRepository,
    private val authUtils: AuthenticationUtil
) : BaseViewModel() {

    val users = MutableLiveData(listOf<User>())

    private val _inProgress = MutableLiveData(false)
    val inProgress: LiveData<Boolean>
        get() = _inProgress

    fun loadUsers(authUtils: AuthenticationUtil) {
        viewModelScope.launch(Dispatchers.IO) {
            users.postValue(authUtils.getAllUsers())
        }
    }

    fun addAccount(v: View) {
        val url =
            "https://github.com/login/oauth/authorize?client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI&scope=repo user&state=$STATE"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)

        v.context.startActivity(i)
    }

    fun getAccessToken(code: String) {
        viewModelScope.launch {
            _inProgress.postValue(true)
            when (val result = userRepository.getAccessToken(code)) {
                is ApiResult.Success -> {
                    result.data.run {
                        val userApiResult =
                            userRepository.profile("$tokenType $accessToken")
                        when (userApiResult) {
                            is ApiResult.Success -> {
                                authUtils.addAccount(accessToken, userApiResult.data)

                                val navOptions = NavOptions.Builder()
                                    .setPopUpTo(R.id.nav_home, true)
                                    .build()
                                navigator?.navigate(
                                    R.id.action_nav_authorize_to_nav_home,
                                    null,
                                    navOptions
                                )
                            }
                            is ApiResult.Error -> {
                                // TODO: 5/1/20 Handle Error
                            }
                        }
                    }
                }
                is ApiResult.Error -> {
                    // TODO: 5/1/20 Handle Error
                }
            }
            _inProgress.postValue(false)
        }
    }
}

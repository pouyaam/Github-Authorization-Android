package com.mydigipay.challenge.ui.authorize

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.auth.AuthenticationUtil
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthorizeViewModel : BaseViewModel() {

    val users = MutableLiveData(listOf<User>())

    fun loadUsers(authUtils: AuthenticationUtil) {
        viewModelScope.launch(Dispatchers.IO) {
            users.postValue(authUtils.getAllUsers())
        }
    }
}

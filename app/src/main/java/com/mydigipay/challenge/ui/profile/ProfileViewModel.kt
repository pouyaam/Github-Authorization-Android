package com.mydigipay.challenge.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.pojo.User
import kotlinx.coroutines.launch

class ProfileViewModel(private val model: ProfileModel) : BaseViewModel(model) {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user


    fun goToSearchRepository() {
        navigateTo(ProfileFragmentDirections.actionProfileToSearch())
    }

    init {
        getAuthenticatedUser()
    }


    private fun getAuthenticatedUser() = viewModelScope.launch {
        when (val result = model.getAuthenticatedUser()) {
            is ApiResult.Success -> _user.postValue(result.data)
            is ApiResult.Error -> {
            }
        }
    }

}
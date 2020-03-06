package com.mydigipay.challenge.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.model.User
import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.repository.user.UserRepository
import com.mydigipay.challenge.util.ktx.launch

class ProfileViewModel(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    val profileInfo = _user.map {
        listOf(
            StringItem(
                1,
                R.drawable.ic_email,
                R.string.email,
                it?.email ?: "Not filled"
            ),
            StringItem(
                2,
                R.drawable.ic_location,
                R.string.location,
                it?.location ?: "Not filled"
            ),
            StringItem(
                3,
                R.drawable.ic_work,
                R.string.company,
                it?.company ?: "Not filled"
            ),
            StringItem(
                4,
                R.drawable.ic_web,
                R.string.blog,
                it?.blog ?: "Not filled"
            )
        )
    }

    init {
        getUser()
    }

    private fun getUser() = launch {
        when (val result = userRepository.getAuthenticatedUser()) {
            is ApiResult.Success -> _user.postValue(result.data)
            is ApiResult.Error -> showSnackBar(result.message)
        }
    }

    fun onEditProfileClicked() = showSnackBar(R.string.soon)

    fun onBackPressed(): Boolean {
        navigateBackTo(R.id.repositories, false)
        return true
    }
}
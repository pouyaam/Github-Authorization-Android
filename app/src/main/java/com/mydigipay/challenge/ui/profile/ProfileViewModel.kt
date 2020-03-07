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
import com.mydigipay.challenge.util.ktx.toHumanReadableFormat

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
                if (!it?.email.isNullOrBlank()) it.email else "Not filled"
            ),
            StringItem(
                2,
                R.drawable.ic_location,
                R.string.location,
                if (!it?.location.isNullOrBlank()) it.location else "Not filled"
            ),
            BadgeItem(
                3,
                R.drawable.ic_following,
                R.string.followings,
                it?.following?.toHumanReadableFormat() ?: "0"
            ),
            BadgeItem(
                4,
                R.drawable.ic_follower,
                R.string.followers,
                it?.followers?.toHumanReadableFormat() ?: "0"
            ),
            StringItem(
                5,
                R.drawable.ic_work,
                R.string.company,
                if (!it?.company.isNullOrBlank()) it.company else "Not filled"
            ),
            StringItem(
                6,
                R.drawable.ic_web,
                R.string.blog,
                if (!it?.blog.isNullOrBlank()) it.blog else "Not filled"
            ),
            BadgeItem(
                7,
                R.drawable.ic_repo,
                R.string.repositories,
                it?.publicRepos?.toHumanReadableFormat() ?: "0"
            ),
            BadgeItem(
                8,
                R.drawable.ic_gist,
                R.string.gists,
                it?.publicGists?.toHumanReadableFormat() ?: "0"
            )
        )
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _requestFailed = MutableLiveData<Boolean>()
    val requestFailed: LiveData<Boolean>
        get() = _requestFailed

    init {
        getAuthenticatedUser()
    }

    fun getAuthenticatedUser() = launch {
        _isLoading.postValue(true)
        _requestFailed.postValue(false)
        when (val result = userRepository.getAuthenticatedUser()) {
            is ApiResult.Success -> _user.postValue(result.data)
            is ApiResult.Error -> {
                showSnackBar(result.error.message)
                _requestFailed.postValue(true)
            }
        }
        _isLoading.postValue(false)
    }

    fun onEditProfileClicked() = showSnackBar(R.string.soon)

    fun onBackPressed(): Boolean {
        navigateBackTo(R.id.repositories, false)
        return true
    }
}
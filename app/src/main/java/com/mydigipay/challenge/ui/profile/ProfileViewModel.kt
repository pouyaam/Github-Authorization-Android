package com.mydigipay.challenge.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.models.Repo
import com.mydigipay.challenge.data.models.User
import com.mydigipay.challenge.data.repositories.ApiResult
import com.mydigipay.challenge.data.repositories.user.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _inProgress = MutableLiveData(false)
    val inProgress: LiveData<Boolean>
        get() = _inProgress

    private val _repos = MutableLiveData<MutableList<Repo>>(mutableListOf())

    val repos: LiveData<MutableList<Repo>>
        get() = _repos

    fun setUser(user: User?) {
        loadUser()

        user?.let {
            loadRepos(1)
            _user.postValue(user)
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            when (val result = userRepository.profile(null)) {
                is ApiResult.Success -> {
                    if (_user.value == null)
                        loadRepos(1)
                    _user.postValue(result.data)
                }
                is Error -> {
                }
            }
        }
    }

    fun loadRepos(page: Int) {
        viewModelScope.launch {
            when (val result = userRepository.repos(page)) {
                is ApiResult.Success -> {
                    val currentList: MutableList<Repo> =
                        (if (_repos.value != null) _repos.value else mutableListOf()) as MutableList<Repo>
                    currentList.addAll(result.data)
                    _repos.value = currentList
                }
                is ApiResult.Error -> {
                    // TODO: 4/27/20 Display Error
                }
            }

        }
    }
}
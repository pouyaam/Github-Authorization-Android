package com.mydigipay.challenge.ui.repo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.models.Repo

class RepoViewModel : BaseViewModel() {

    val repo = MutableLiveData<Repo?>()

    private val _branches = Transformations.map(repo) {
        it?.let {

        }
    }
}

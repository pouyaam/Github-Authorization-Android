package com.mydigipay.challenge.ui.repo

import androidx.lifecycle.MutableLiveData
import com.mydigipay.challenge.base.BaseViewModel
import com.mydigipay.challenge.data.models.Repo

class RepoViewModel : BaseViewModel() {


    val repo = MutableLiveData<Repo?>();
}

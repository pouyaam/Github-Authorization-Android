package com.mydigipay.challenge.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mydigipay.challenge.base.BaseViewModel

class ProfileViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is bottom_nav_menu Fragment"
    }
    val text: LiveData<String> = _text
}
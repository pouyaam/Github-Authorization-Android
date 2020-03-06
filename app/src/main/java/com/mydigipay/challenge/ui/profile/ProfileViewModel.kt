package com.mydigipay.challenge.ui.profile

import com.mydigipay.challenge.base.BaseViewModel

class ProfileViewModel(model: ProfileModel) : BaseViewModel(model) {

    fun goToSearchrepository() {
        navigateTo(ProfileFragmentDirections.actionProfileToSearch())
    }
}
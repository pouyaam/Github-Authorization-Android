package com.mydigipay.challenge.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    var navigator: Navigator? = null
}
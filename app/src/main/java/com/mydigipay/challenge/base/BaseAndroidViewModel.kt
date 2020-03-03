package com.mydigipay.challenge.base

import android.app.Application
import com.mydigipay.challenge.app.App

abstract class BaseAndroidViewModel(
    application: Application
) : BaseViewModel() {

    protected val app = application as App

}
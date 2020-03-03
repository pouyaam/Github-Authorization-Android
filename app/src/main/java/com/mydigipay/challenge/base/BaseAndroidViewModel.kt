package com.mydigipay.challenge.base

import android.app.Application
import androidx.annotation.StringRes
import com.mydigipay.challenge.app.App

abstract class BaseAndroidViewModel(
    application: Application
) : BaseViewModel() {

    protected val app = application as App

    protected fun getString(@StringRes stringRes: Int, vararg formatArgs: Any?): String {
        return app.resources.getString(stringRes, *formatArgs)
    }

}
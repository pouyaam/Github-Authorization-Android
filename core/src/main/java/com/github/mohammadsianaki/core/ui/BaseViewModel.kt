package com.github.mohammadsianaki.core.ui

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<Parameters> : ViewModel() {

    abstract fun makeData(params: Parameters? = null)
}
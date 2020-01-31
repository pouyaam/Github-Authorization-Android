package com.mydigipay.challenge.presentation.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.mohammadsianaki.core.model.Resource
import com.github.mohammadsianaki.core.ui.BaseViewModel

abstract class ListViewModel<T : ItemModel, Parameters> : BaseViewModel<Parameters>() {
    protected val liveData = MutableLiveData<Resource<List<T>>>()
    fun getLiveData(): LiveData<Resource<List<T>>> = liveData
}
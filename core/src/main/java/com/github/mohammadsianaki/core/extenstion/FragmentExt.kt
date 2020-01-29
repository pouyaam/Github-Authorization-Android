package com.github.mohammadsianaki.core.extenstion

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.github.mohammadsianaki.core.model.Resource
import org.koin.androidx.viewmodel.ext.android.getViewModel

inline fun <reified T : ViewModel> Fragment.createViewModel(
    body: T.() -> Unit = {}
) = getViewModel<T>().apply { body() }


fun <T : Any, Live : LiveData<Resource<T>>> Fragment.observe(
    liveData: Live,
    body: (Resource<T>?) -> Unit
) = liveData.observe(viewLifecycleOwner, Observer(body))
package com.mydigipay.challenge.core.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun initView()
    abstract fun subscribeViews()
}

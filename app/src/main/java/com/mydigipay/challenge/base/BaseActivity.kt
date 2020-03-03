package com.mydigipay.challenge.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    abstract val viewModel: VM

    abstract val layoutRes: Int

    abstract val navigationId: Int

    val binding by lazy {
        DataBindingUtil.setContentView(this, layoutRes) as DB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        bindObservables()
        oneTimeEvent()
    }

    override fun onStart() {
        super.onStart()
        everyTimeEvent()
    }

    /**
     *
     *  You need override this method.
     *  And you need to set viewModel to binding: binding.viewModel = viewModel
     *
     */
    abstract fun initBinding()

    open fun bindObservables() {}

    open fun oneTimeEvent() {}

    open fun everyTimeEvent() {}

    fun getCurrentFragment(): Fragment? {
        return supportFragmentManager
            .findFragmentById(navigationId)
            ?.childFragmentManager
            ?.primaryNavigationFragment
    }

}

package com.mydigipay.challenge.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {

    abstract val viewModel: VM
    open val sharedViewModel: BaseViewModel? = null

    abstract val layoutRes: Int

    open var binding: DB? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        init(inflater, container)
        initBinding()
        bindObservables()
        oneTimeEvent()
        return binding?.root ?: View(context)
    }

    private fun init(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}


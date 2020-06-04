package com.mydigipay.challenge.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/3/2020.
 *
 * mahdiZTD@gmail.com
 */

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment() {

    private var mRootView: View? = null

    lateinit var viewDataBinding: T
        private set
    private var viewModel: V? = null


    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val mViewModel: V


    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        viewModel = mViewModel
        setHasOptionsMenu(false)
    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(false)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mRootView = viewDataBinding.root
        return mRootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.setVariable(bindingVariable, mViewModel)
        viewDataBinding.executePendingBindings()
    }


    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

}

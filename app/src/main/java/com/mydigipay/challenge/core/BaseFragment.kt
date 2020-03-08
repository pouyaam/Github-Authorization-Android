package com.mydigipay.challenge.core

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.transition.TransitionInflater
import org.koin.android.ext.android.inject

abstract class BaseFragment : Fragment() {

    val sharedPref: SharedPreferences by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onBindView(inflater, container, savedInstanceState)
    }

    abstract fun onBindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewBounded(view, savedInstanceState)
    }

    open fun onViewBounded(view: View, savedInstanceState: Bundle?) {}

    protected fun bindView(
        inflater: LayoutInflater,
        layoutResId: Int,
        container: ViewGroup?,
        variableId: Int,
        viewModel: ViewModel
    ): View {
        return DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutResId, container, false)
            .apply {
                setVariable(variableId, viewModel)
                executePendingBindings()
            }.root
    }

    protected fun bindView(
        inflater: LayoutInflater,
        layoutResId: Int,
        container: ViewGroup?,
        variables: Map<Int, ViewModel>
    ): View {
        return DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutResId, container, false)
            .apply {
                variables.forEach { (variableId, viewModel) ->
                    setVariable(variableId, viewModel)
                }
                executePendingBindings()
            }.root
    }

    fun isValid(): Boolean {
        return activity != null && isAdded
    }

    override fun onStart() {
        super.onStart()
        activity?.intent?.let {
            intentHandle(it)
        }
    }

    open fun intentHandle(intent: Intent) {}
}
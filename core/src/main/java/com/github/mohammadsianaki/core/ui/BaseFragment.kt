package com.github.mohammadsianaki.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mohammadsianaki.core.R
import com.github.mohammadsianaki.core.extenstion.hide
import com.github.mohammadsianaki.core.extenstion.inflate
import com.github.mohammadsianaki.core.extenstion.show
import com.github.mohammadsianaki.core.model.ErrorHolder
import com.github.mohammadsianaki.core.networkconnection.NetworkListener
import com.github.mohammadsianaki.core.networkconnection.manager.ReceiverManager

abstract class BaseFragment : Fragment(), NetworkListener {
    abstract val layoutId: Int
    private val emptyViewLayoutId: Int = R.layout.layout_empty_view
    private val errorViewLayoutId: Int = R.layout.layout_error_view
    private var loadingView: View? = null
    private var emptyViewGroup: ViewGroup? = null
    private var errorViewGroup: ViewGroup? = null
    protected abstract fun retryLoadData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(layoutId)?.apply {
            loadingView = findViewById(R.id.loading)
            errorViewGroup = findViewById(R.id.errorView)
            emptyViewGroup = findViewById(R.id.emptyView)
            initEmptyView(container)
        }
    }

    override fun onResume() {
        super.onResume()
        ReceiverManager.registerNetworkChangeReceiver(requireActivity(), this)
    }

    override fun onPause() {
        super.onPause()
        ReceiverManager.unregisterNetworkChangeReceiver(requireActivity(), this)
    }

    override fun onDestroyView() {
        loadingView = null
        emptyViewGroup?.removeAllViews()
        emptyViewGroup = null
        errorViewGroup?.removeAllViews()
        errorViewGroup = null
        super.onDestroyView()
    }

    protected open fun initEmptyView(container: ViewGroup) {
        emptyViewGroup?.addView(
            layoutInflater.inflate(emptyViewLayoutId, container, false)
        )
    }

    protected fun showLoading() = loadingView?.show()

    protected fun hideLoading() = loadingView?.hide()

    protected fun showEmptyView() = emptyViewGroup?.show()

    protected fun hideEmptyView() = emptyViewGroup?.hide()

    protected open fun showErrorView(errorHolder: ErrorHolder?) {
        errorHolder?.let {
            errorViewGroup?.removeAllViews()
            val layoutId = when (errorHolder) {
                is ErrorHolder.NetworkConnection -> R.layout.layout_network_error
                is ErrorHolder.Server -> R.layout.layout_error_view
                is ErrorHolder.UnExpected -> R.layout.layout_error_view
            }
            errorViewGroup?.addView(layoutInflater.inflate(layoutId, null, false))
            errorViewGroup?.findViewById<View>(R.id.retry)?.setOnClickListener {
                retryLoadData()
            }
            errorViewGroup?.show()
            it
        }
    }

    protected fun hideErrorView() = errorViewGroup?.hide()
}
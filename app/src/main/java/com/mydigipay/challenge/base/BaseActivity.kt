package com.mydigipay.challenge.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import com.mydigipay.challenge.data.network.ConnectionLiveData
import com.mydigipay.challenge.util.FragmentOnBackPressed
import com.mydigipay.challenge.util.addNavigatorOn
import com.mydigipay.challenge.util.observeActions

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
        initActions()
        initNavigator()
        checkInternetConnection()
        oneTimeEvent()
    }

    private fun initActions() = observeActions(viewModel)

    private fun initNavigator() =
        addNavigatorOn(viewModel,  Navigation.findNavController(this, navigationId))

    private fun checkInternetConnection() {
        ConnectionLiveData.observe(this) { isConnected ->
            onNetworkStateChanged(isConnected)
        }
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

    open fun onNetworkStateChanged(isConnected: Boolean) {}

    fun getCurrentFragment(): Fragment? {
        return supportFragmentManager
            .findFragmentById(navigationId)
            ?.childFragmentManager
            ?.primaryNavigationFragment
    }

    override fun onBackPressed() {
        val currentFragment = getCurrentFragment()
        if (currentFragment !is FragmentOnBackPressed) super.onBackPressed()
        (currentFragment as? FragmentOnBackPressed)?.onBackPressed()?.let {
            if (it) super.onBackPressed()
        }
    }

}

package com.mydigipay.challenge.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mydigipay.challenge.R
import com.mydigipay.challenge.auth.AuthenticationState.SINGLE_AUTHENTICATION
import com.mydigipay.challenge.auth.AuthenticationUtil
import com.mydigipay.challenge.ui.HomeActivity
import org.koin.android.ext.android.inject

abstract class BaseFragment<V : BaseViewModel, B : ViewDataBinding> : Fragment(), Navigator {

    abstract val viewModel: V
    abstract val layoutId: Int
    open val needAuthentication = false

    open lateinit var binding: B
    lateinit var navController: NavController
    val authUtil: AuthenticationUtil by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        navController = findNavController()
        viewModel.navigator = this

        checkAuth()
        setBindingVar()
        return binding.root
    }

    private fun checkAuth() {
        if (needAuthentication &&
            authUtil.authenticationState() != SINGLE_AUTHENTICATION
        ) {
            navigate(R.id.action_global_nav_authorize)
        }
    }

    abstract fun setBindingVar()

    override fun navigate(@IdRes direction: Int, args: Bundle?) {
        navController.navigate(direction)
    }

    override fun popBackStack(@IdRes direction: Int) {
        navController.popBackStack()

    }

    override fun popBackStackTo(destination: Int, inclusive: Boolean) {
        navController.popBackStack(destination, inclusive)
    }

    fun getHomeActivity(): HomeActivity? = activity as? HomeActivity
}
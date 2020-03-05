package com.mydigipay.challenge.base

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.mo22sen.batmanmovies.utils.EventObserver
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.utils.NavigationCommand
import com.mydigipay.challenge.utils.interfaces.FragmentOnBackPressed
import com.mydigipay.challenge.utils.ktx.navigateUpOrFinish
import org.koin.android.ext.android.inject

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()
    abstract val viewModel: VM
    abstract val layoutRes: Int
    abstract val navigationId: Int
    val binding by lazy {
        DataBindingUtil.setContentView(this, layoutRes) as DB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        configEvents()
        bindObservables()
        observeNavigation()
    }

    private fun observeNavigation() {
        viewModel.navigationCommand.observe(this, EventObserver { command ->
            val navController = Navigation.findNavController(this, navigationId)
            when (command) {
                is NavigationCommand.To ->
                    navController.navigate(command.directions)
                is NavigationCommand.ToWithFinish -> {
                    navController.navigate(command.directions)
                    finish()
                }
                is NavigationCommand.ToAction ->
                    navController.navigate(command.actionId)
                is NavigationCommand.ToActionWithFinish -> {
                    navController.navigate(command.actionId)
                    finish()
                }
                is NavigationCommand.Back ->
                    navController.navigateUpOrFinish(this)
                is NavigationCommand.BackTo ->
                    navController.popBackStack(command.destinationId, command.inclusive)
                is NavigationCommand.ToRoot -> {
                    navController.popBackStack(R.id.home, false)
                }
            }
        })
    }


    abstract fun configEvents()


    abstract fun bindObservables()


    abstract fun initBinding()


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onBackPressed() {
        val currentFragment = getCurrentFragment()
        if (currentFragment !is FragmentOnBackPressed) super.onBackPressed()
        (currentFragment as? FragmentOnBackPressed)?.onBackPressed()?.let {
            if (it) super.onBackPressed()
        }
    }

    protected fun getCurrentFragment(): Fragment? {
        val navHostFragment = supportFragmentManager.findFragmentById(navigationId)
        val currentFragments = navHostFragment?.childFragmentManager?.fragments
        return currentFragments?.let {
            if (it.isNotEmpty()) it[0]
            else null
        }
    }

}

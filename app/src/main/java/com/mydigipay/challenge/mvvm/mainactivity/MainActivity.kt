package com.mydigipay.challenge.mvvm.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.github.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    HasSupportFragmentInjector {

    private lateinit var viewDataBinding: ActivityMainBinding
    private lateinit var navController: NavController

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInjection()
        performDataBinding()
        initialToolbar()
    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        fragmentDispatchingAndroidInjector

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }


    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }


    private fun initialToolbar() {
        navController = findNavController(R.id.nav_host)
        setupActionBarWithNavController(this, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}

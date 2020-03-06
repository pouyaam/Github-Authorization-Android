package com.mydigipay.challenge.ui.home

import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.navigation.NavigationView
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseActivity
import com.mydigipay.challenge.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {

    override val viewModel: HomeViewModel by viewModel()
    override val layoutRes: Int = R.layout.activity_home
    override val navigationId: Int = R.id.nav_host

    override fun initBinding() {
        binding.apply {
            lifecycleOwner = this@HomeActivity
            vm = viewModel
            executePendingBindings()
        }
    }

    override fun oneTimeEvent() {
        setSupportActionBar(bottom_bar)
        setupNavController(bottom_bar, navigation_view, drawer)
    }

    private fun setupNavController(
        bottomBar: BottomAppBar,
        navigationView: NavigationView?,
        drawer: DrawerLayout?
    ) {
        Navigation.findNavController(this, navigationId).run {
            bottomBar.setupWithNavController(this, drawer)
            navigationView?.setupWithNavController(this)
        }
    }

    fun toggleBottomSection(show: Boolean) {
        if (show) {
            bottom_bar.performShow()
            fab.show()
        } else {
            bottom_bar.performHide()
            fab.hide()
        }
    }
}

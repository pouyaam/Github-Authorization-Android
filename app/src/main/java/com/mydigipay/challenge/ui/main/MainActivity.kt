package com.mydigipay.challenge.ui.main

import com.mydigipay.challenge.base.BaseActivity
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.github.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModel()

    override val layoutRes: Int = R.layout.activity_main

    override val navigationId: Int = R.id.main_navigation

    override fun configEvents() {
    }

    override fun bindObservables() {
    }

    override fun initBinding() {
        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
            executePendingBindings()
        }
    }

}

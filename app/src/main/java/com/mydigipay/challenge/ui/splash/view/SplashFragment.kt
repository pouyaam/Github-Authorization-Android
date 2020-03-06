package com.mydigipay.challenge.ui.splash.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mydigipay.challenge.core.NavBaseFragment
import com.mydigipay.challenge.github.BR
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.ui.splash.viewmodel.SplashViewModel
import org.koin.android.ext.android.inject

class SplashFragment : NavBaseFragment() {

    private val viewModel: SplashViewModel by inject()

    override fun onBindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return bindView(inflater, R.layout.splash_fragment, container, BR.vm, viewModel)
    }

    override fun onViewBounded(view: View, savedInstanceState: Bundle?) {
        bindNavigator(view, viewModel)
        viewModel.navigation()
    }
}
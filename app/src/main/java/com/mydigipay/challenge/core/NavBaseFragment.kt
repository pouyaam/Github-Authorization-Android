package com.mydigipay.challenge.core

import android.view.View
import androidx.navigation.Navigation

abstract class NavBaseFragment : BaseFragment() {

    fun bindNavigator(view: View, viewModel: RxNavBaseViewModel) {
        viewModel.navigator = Navigation.findNavController(view)
        viewModel.bindNavigator()
    }

}
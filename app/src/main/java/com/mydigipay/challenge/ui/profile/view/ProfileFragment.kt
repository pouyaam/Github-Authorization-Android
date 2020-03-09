package com.mydigipay.challenge.ui.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mydigipay.challenge.core.NavBaseFragment
import com.mydigipay.challenge.github.BR
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.ui.profile.viewmodel.ProfileViewModel
import org.koin.android.ext.android.inject

class ProfileFragment : NavBaseFragment() {

    private val viewModel: ProfileViewModel by inject()

    override fun onBindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return bindView(inflater, R.layout.profile_fragment, container, BR.vm, viewModel)
    }

    override fun onViewBounded(view: View, savedInstanceState: Bundle?) {
        bindNavigator(view, viewModel)
    }
}
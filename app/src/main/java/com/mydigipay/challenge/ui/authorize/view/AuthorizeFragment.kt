package com.mydigipay.challenge.ui.authorize.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mydigipay.challenge.core.BaseFragment
import com.mydigipay.challenge.github.BR
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.ui.authorize.viewmodel.AuthorizeViewModel
import org.koin.android.ext.android.inject

class AuthorizeFragment : BaseFragment() {

    private val viewModel: AuthorizeViewModel by inject()

    override fun onBindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return bindView(inflater, R.layout.authorize_fragment, container, BR.auth_vm, viewModel)
    }

    override fun onViewBound(view: View?, savedInstanceState: Bundle?) {
    }
}
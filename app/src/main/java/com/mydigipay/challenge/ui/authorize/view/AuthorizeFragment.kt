package com.mydigipay.challenge.ui.authorize.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mydigipay.challenge.core.NavBaseFragment
import com.mydigipay.challenge.github.BR
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.ui.authorize.viewmodel.AuthorizeViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AuthorizeFragment : NavBaseFragment() {

    private val viewModel: AuthorizeViewModel by inject { parametersOf(this) }

    override fun onBindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return bindView(inflater, R.layout.authorize_fragment, container, BR.vm, viewModel)
    }

    override fun onViewBounded(view: View, savedInstanceState: Bundle?) {
        bindNavigator(view, viewModel)
    }

    override fun intentHandle(intent: Intent) {
        super.intentHandle(intent)
        intent.data?.getQueryParameter("code")?.let {
            viewModel.setCode(it)
        }
    }
}
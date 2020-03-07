package com.mydigipay.challenge.ui.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mydigipay.challenge.core.BaseFragment
import com.mydigipay.challenge.github.BR
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.ui.detail.viewmodel.DetailViewModel
import org.koin.android.ext.android.inject

class DetailFragment : BaseFragment() {

    private val viewModel: DetailViewModel by inject()

    override fun onBindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return bindView(inflater, R.layout.detail_fragment, container, BR.detail_vm, viewModel)
    }

    override fun onViewBound(view: View?, savedInstanceState: Bundle?) {

    }
}
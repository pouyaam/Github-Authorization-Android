package com.mydigipay.challenge.ui.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mydigipay.challenge.core.BaseFragment
import com.mydigipay.challenge.github.BR
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.ui.search.viewmodel.SearchViewModel
import org.koin.android.ext.android.inject

class SearchFragment : BaseFragment() {

    private val viewModel: SearchViewModel by inject()

    override fun onBindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return bindView(inflater, R.layout.search_fragment, container, BR.search_vm, viewModel)
    }

    override fun onViewBound(view: View?, savedInstanceState: Bundle?) {
    }
}
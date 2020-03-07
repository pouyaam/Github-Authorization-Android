package com.mydigipay.challenge.ui.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mydigipay.challenge.core.NavBaseFragment
import com.mydigipay.challenge.github.BR
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.ui.search.viewmodel.SearchViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SearchFragment : NavBaseFragment() {

    private val viewModel: SearchViewModel by inject { parametersOf(this) }

    override fun onBindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return bindView(inflater, R.layout.search_fragment, container, BR.vm, viewModel)
    }

    override fun onViewBounded(view: View, savedInstanceState: Bundle?) {
        bindNavigator(view, viewModel)
    }
}
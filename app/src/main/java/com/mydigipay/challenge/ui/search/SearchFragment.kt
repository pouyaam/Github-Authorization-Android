package com.mydigipay.challenge.ui.search

import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.github.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>() {

    override val viewModel: SearchViewModel by viewModel()

    override val layoutRes: Int = R.layout.fragment_search

    override fun configEvents() {
    }

    override fun bindObservables() {
    }

    override fun initBinding() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            executePendingBindings()
        }
    }
}
package com.mydigipay.challenge.ui.repos

import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentRepositoriesListBinding
import org.koin.android.ext.android.inject

class SearchRepositoryFragment :
    BaseFragment<SearchRepositoriesViewModel, FragmentRepositoriesListBinding>() {
    override val viewModel: SearchRepositoriesViewModel by inject()
    override val layoutId: Int = R.layout.fragment_search_repositories

    override fun setBindingVar() {
        binding.viewModel = viewModel
        viewModel.search("android")
    }
}
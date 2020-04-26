package com.mydigipay.challenge.ui.repos

import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentSearchRepositoriesBinding
import org.koin.android.ext.android.inject

class SearchRepositoryFragment :
    BaseFragment<SearchRepositoriesViewModel, FragmentSearchRepositoriesBinding>() {
    override val viewModel: SearchRepositoriesViewModel by inject()
    override val layoutId: Int = R.layout.fragment_search_repositories

    override fun setBindingVar() {
        binding.vm = viewModel
//        viewModel.search("android")
    }
}
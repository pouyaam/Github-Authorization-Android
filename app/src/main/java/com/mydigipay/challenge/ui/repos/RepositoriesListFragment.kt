package com.mydigipay.challenge.ui.repos

import android.os.Bundle
import android.view.View
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentRepositoriesListBinding
import org.koin.android.ext.android.inject

class RepositoriesListFragment :
    BaseFragment<RepositoriesListViewModel, FragmentRepositoriesListBinding>() {
    override val viewModel: RepositoriesListViewModel by inject()
    override val layoutId: Int = R.layout.fragment_repositories_list

    override fun setBindingVar() {
        binding.viewModel = viewModel
        viewModel.search("android")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

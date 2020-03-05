package com.mydigipay.challenge.ui.home

import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.github.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by viewModel()

    override val layoutRes: Int = R.layout.fragment_home

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
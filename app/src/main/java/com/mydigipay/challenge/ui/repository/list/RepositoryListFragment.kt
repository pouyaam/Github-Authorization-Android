package com.mydigipay.challenge.ui.repository.list

import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentRipositoryListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryListFragment :
    BaseFragment<RepositoryListViewModel, FragmentRipositoryListBinding>() {

    override val viewModel: RepositoryListViewModel by viewModel()
    override val layoutRes: Int = R.layout.fragment_ripository_list

    override fun initBinding() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            executePendingBindings()
        }
    }
}
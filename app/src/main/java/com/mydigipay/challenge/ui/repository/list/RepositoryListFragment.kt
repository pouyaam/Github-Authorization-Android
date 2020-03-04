package com.mydigipay.challenge.ui.repository.list

import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentRipositoryListBinding
import kotlinx.android.synthetic.main.fragment_ripository_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryListFragment :
    BaseFragment<RepositoryListViewModel, FragmentRipositoryListBinding>() {

    override val viewModel: RepositoryListViewModel by viewModel()
    override val layoutRes: Int = R.layout.fragment_ripository_list
    private val adapter: GitRepoAdapter by inject()

    override fun oneTimeEvent() {
        repo_list.adapter = adapter
    }

    override fun initBinding() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            executePendingBindings()
        }
    }
}
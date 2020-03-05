package com.mydigipay.challenge.ui.repository.detail

import androidx.navigation.fragment.navArgs
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentRepositoryDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryDetailFragment : BaseFragment<RepositoryDetailViewModel, FragmentRepositoryDetailBinding>() {

    override val viewModel: RepositoryDetailViewModel by viewModel()
    override val layoutRes: Int = R.layout.fragment_repository_detail
    private val args: RepositoryDetailFragmentArgs by navArgs()

    override fun oneTimeEvent() {
        viewModel.setGitRepo(args.gitRepo)
    }

    override fun initBinding() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            executePendingBindings()
        }
    }
}
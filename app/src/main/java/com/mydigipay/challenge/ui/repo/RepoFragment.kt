package com.mydigipay.challenge.ui.repo

import androidx.navigation.fragment.navArgs
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentRepoBinding
import org.koin.android.ext.android.inject

class RepoFragment : BaseFragment<RepoViewModel, FragmentRepoBinding>() {

    override val viewModel: RepoViewModel by inject()
    override val layoutId: Int = R.layout.fragment_repo
    val args: RepoFragmentArgs by navArgs()

    override fun setBindingVar() {
        viewModel.repo.value = args.argumentRepo
        binding.vm = viewModel
    }
}

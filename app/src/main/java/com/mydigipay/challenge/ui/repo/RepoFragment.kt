package com.mydigipay.challenge.ui.repo

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentRepoBinding
import com.mydigipay.challenge.util.ext.addNewTab
import org.koin.android.ext.android.inject

class RepoFragment : BaseFragment<RepoViewModel, FragmentRepoBinding>() {

    override val viewModel: RepoViewModel by inject()
    override val layoutId: Int = R.layout.fragment_repo
    private val args: RepoFragmentArgs by navArgs()

    override fun setBindingVar() {
        args.argumentRepo.let {
            viewModel.repo.value = it
            viewModel.loadBranches(it)
        }
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configTabLayout()
    }

    private fun configTabLayout() {
        binding.fragmentRepoBranchesTl.apply {

            viewModel.branch.observe(viewLifecycleOwner, Observer { branches ->
                branches.takeIf {
                    !it.isNullOrEmpty()
                }?.sortedBy {
                    it.name == viewModel.repo.value?.defaultBranch
                }?.forEach {
                    addNewTab(it.name)
                }
            })

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    viewModel.changeBranch(tab?.text.toString())
                }
            })
        }

    }
}

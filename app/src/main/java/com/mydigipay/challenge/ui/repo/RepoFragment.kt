package com.mydigipay.challenge.ui.repo

import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentRepoBinding
import com.mydigipay.challenge.ui.adapters.CommitAdapter
import com.mydigipay.challenge.util.EndlessRecyclerViewScrollListener
import com.mydigipay.challenge.util.ext.onTabSelected
import org.koin.android.ext.android.inject
import java.util.*

class RepoFragment : BaseFragment<RepoViewModel, FragmentRepoBinding>() {

    override val viewModel: RepoViewModel by inject()
    override val layoutId: Int = R.layout.fragment_repo
    private val args: RepoFragmentArgs by navArgs()

    private val commitAdapter = CommitAdapter {}

    override fun setBindingVar() {
        configTabLayout()
        configList()
        args.argumentRepo.let {
            viewModel.repo.value = it
            viewModel.loadBranches(it)
        }
        binding.vm = viewModel
    }

    private fun configList() {
        binding.fragmentRepoCommitListRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = commitAdapter

            addOnScrollListener(object :
                EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int) {
                    viewModel.getCommits(page)
                }
            })
        }
    }


    private fun configTabLayout() {
        binding.fragmentRepoBranchesTl.onTabSelected {
            it?.run {
                viewModel.changeBranch(
                    text
                        .toString()
                        .toLowerCase(Locale.ROOT)
                )
            }
        }
    }
}

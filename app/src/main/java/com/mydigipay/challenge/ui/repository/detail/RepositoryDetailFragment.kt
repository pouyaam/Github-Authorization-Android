package com.mydigipay.challenge.ui.repository.detail

import androidx.navigation.fragment.navArgs
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentRepositoryDetailBinding
import com.mydigipay.challenge.util.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_repository_detail.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryDetailFragment : BaseFragment<RepositoryDetailViewModel, FragmentRepositoryDetailBinding>() {

    override val viewModel: RepositoryDetailViewModel by viewModel()
    override val layoutRes: Int = R.layout.fragment_repository_detail
    private val args: RepositoryDetailFragmentArgs by navArgs()
    private val adapter: CommitAdapter by inject()

    private var endlessScroller: EndlessRecyclerViewScrollListener? = null

    override fun oneTimeEvent() {
        viewModel.setGitRepo(args.gitRepo)
        configList()
    }

    private fun configList() {
        commit_list?.apply {
            adapter = this@RepositoryDetailFragment.adapter
            layoutManager?.let { layoutManager ->
                endlessScroller =
                    object : EndlessRecyclerViewScrollListener(layoutManager) {
                        override fun onLoadMore(page: Int) {
                            viewModel.getCommits(page)
                        }
                    }.also { endlessScroller ->
                        addOnScrollListener(endlessScroller)
                    }
            }
        }
    }

    override fun initBinding() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            executePendingBindings()
        }
    }
}
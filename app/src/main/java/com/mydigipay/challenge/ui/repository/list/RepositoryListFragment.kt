package com.mydigipay.challenge.ui.repository.list

import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentRepositoryListBinding
import com.mydigipay.challenge.util.EndlessRecyclerViewScrollListener
import com.mydigipay.challenge.util.livedata.observeEvent
import kotlinx.android.synthetic.main.fragment_repository_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryListFragment :
    BaseFragment<RepositoryListViewModel, FragmentRepositoryListBinding>() {

    override val viewModel: RepositoryListViewModel by viewModel()
    override val layoutRes: Int = R.layout.fragment_repository_list
    private val adapter: GitRepoAdapter by inject()

    private var endlessScroller: EndlessRecyclerViewScrollListener? = null

    override fun oneTimeEvent() {
        configList()
    }

    override fun onNetworkStateChanged(isConnected: Boolean) {
        if (isConnected && viewModel.networkPark.first) {
            viewModel.networkPark.second?.let { page ->
                viewModel.searchRepositories(page)
            }
        }
    }

    private fun configList() {
        repo_list?.apply {
            adapter = this@RepositoryListFragment.adapter
            layoutManager?.let { layoutManager ->
                endlessScroller =
                    object : EndlessRecyclerViewScrollListener(layoutManager) {
                        override fun onLoadMore(page: Int) {
                            viewModel.searchRepositories(page)
                        }
                    }.also { endlessScroller ->
                        addOnScrollListener(endlessScroller)
                    }
            }
        }
        adapter.onItemClicked = { gitRepo, _ ->
            viewModel.openRepoDetail(gitRepo)
        }
    }

    override fun bindObservables() {
        viewModel.onQueryChanged.observeEvent(this) {
            viewModel.searchRepositories(1)
        }
        viewModel.onResetList.observeEvent(this) {
            endlessScroller?.resetState()
        }
        viewModel.onFetchDataFailed.observeEvent(this) {
            endlessScroller?.failed()
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
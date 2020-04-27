package com.mydigipay.challenge.ui.repos

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.databinding.FragmentSearchRepositoriesBinding
import com.mydigipay.challenge.ui.adapters.RepoAdapter
import com.mydigipay.challenge.util.EndlessRecyclerViewScrollListener
import com.mydigipay.challenge.util.ext.autoComplete
import org.koin.android.ext.android.inject

class SearchRepositoryFragment :
    BaseFragment<SearchRepositoriesViewModel, FragmentSearchRepositoriesBinding>() {
    override val viewModel: SearchRepositoriesViewModel by inject()
    override val layoutId: Int = R.layout.fragment_search_repositories

    private val repoAdapter = RepoAdapter {
        // TODO: 4/27/20 Navigate To Repo Detail
    }

    override fun setBindingVar() {
        configList()
        binding.vm = viewModel
        viewModel.query.autoComplete(viewLifecycleOwner, 500) {
            viewModel.reSearch()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configList()
        viewModel.query.value = "and"
    }

    private fun configList() {
        binding.repoListRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoAdapter
            addOnScrollListener(object :
                EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int) {
                    viewModel.search(page)
                }
            })
        }
    }
}
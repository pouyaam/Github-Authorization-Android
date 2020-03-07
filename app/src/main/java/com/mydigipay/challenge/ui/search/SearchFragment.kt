package com.mydigipay.challenge.ui.search

import androidx.lifecycle.observe
import com.mydigipay.challenge.base.BaseFragment
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.github.databinding.FragmentSearchBinding
import com.mydigipay.challenge.utils.EndlessRecyclerViewScrollListener
import com.mydigipay.challenge.utils.ktx.logD
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>() {

    override val viewModel: SearchViewModel by viewModel()
    private val adapter: SearchAdapter by inject()

    override val layoutRes: Int = R.layout.fragment_search

    private var endlessScroller: EndlessRecyclerViewScrollListener? = null

    override fun configEvents() {
        list_repository?.apply {
            adapter = this@SearchFragment.adapter
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
    }

    override fun bindObservables() {

        viewModel.repositories.observe(this) {
            logD("data ${it.size}")
        }

        viewModel.onQueryChanged.observe(this) {
            viewModel.searchRepositories(1)
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
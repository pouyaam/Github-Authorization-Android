package com.mydigipay.challenge.presentation.ui.github.search

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.github.mohammadsianaki.core.model.Resource
import com.github.mohammadsianaki.core.model.ResourcesState
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.presentation.core.BaseRecyclerAdapter
import com.mydigipay.challenge.presentation.core.ListFragment
import com.mydigipay.challenge.presentation.core.OnRecyclerItemClickListener
import com.mydigipay.challenge.presentation.model.SearchItemModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SearchFragment : ListFragment<SearchItemModel, String, SearchViewModel>(),
    SearchView.OnQueryTextListener {

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun renderUI(resource: Resource<List<SearchItemModel>>?) {
        resource?.let {
            when (resource.resourcesState) {
                ResourcesState.Loading -> {
                    hideRecyclerView()
                    hideEmptyView()
                    hideErrorView()
                    showLoading()
                    Log.d("<<<SearchFragment>>>", "loading")
                }
                ResourcesState.Success -> {
                    hideLoading()
                    hideEmptyView()
                    hideErrorView()
                    showRecyclerView()
                    handleData(it.data)
                    Log.d("<<<SearchFragment>>>", "success : ${it.data?.size}")
                }
                ResourcesState.Failure -> {
                    hideLoading()
                    hideRecyclerView()
                    hideEmptyView()
                    showErrorView(resource.failure)
                    Log.d("<<<SearchFragment>>>", "failure : ${it.failure}")
                }
            }
        }
    }

    override fun handleData(data: List<SearchItemModel>?) {
        if (data == null || data.isEmpty()) {
            hideLoading()
            hideRecyclerView()
            hideErrorView()
            showEmptyView()
            Log.d("<<<debug:Home>>>>", "null or empty")
        } else {
            Log.d("<<<debug:Home>>>>", "data is full")
            recyclerAdapter.items = data
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        searchView = (menu.findItem(R.id.action_search).actionView as SearchView)
        searchView?.setOnQueryTextListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchView = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null && query.isNotEmpty() && query.isNotBlank()) {
            viewModel.makeData(query)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?) = true
    override val recyclerAdapter: BaseRecyclerAdapter<SearchItemModel> =
        SearchAdapter().apply { onRecyclerItemClickListener = onSearchResultClicked() }

    private fun onSearchResultClicked() = object : OnRecyclerItemClickListener<SearchItemModel> {
        override fun onItemClicked(item: SearchItemModel) {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToDetailFragment(
                    item.searchOwnerModel?.login ?: "",
                    item.name ?: ""
                )
            )
        }
    }

    override fun makeViewModel(): SearchViewModel = getViewModel()
    override fun retryLoadData() {
        viewModel.makeData(searchView?.query.toString())
    }

    override fun onNetworkChanged(connected: Boolean) {
        val query = searchView?.query?.toString()
        if (connected && query != null && query.isNotEmpty() && query.isNotBlank()) {
            viewModel.makeData(query)
        }
    }
}

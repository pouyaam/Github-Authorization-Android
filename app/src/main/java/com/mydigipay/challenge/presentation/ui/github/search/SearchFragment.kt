package com.mydigipay.challenge.presentation.ui.github.search

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import com.github.mohammadsianaki.core.extenstion.observe
import com.github.mohammadsianaki.core.model.Resource
import com.github.mohammadsianaki.core.model.ResourcesState
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.presentation.core.BaseRecyclerAdapter
import com.mydigipay.challenge.presentation.core.ListFragment
import com.mydigipay.challenge.presentation.model.SearchItemModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SearchFragment : ListFragment<SearchItemModel, SearchViewModel>(),
    SearchView.OnQueryTextListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()
        observe(viewModel.getLiveData()) { resource -> renderUI(resource) }
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
        (menu.findItem(R.id.action_search).actionView as SearchView).setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.performSearch(query) }
        return false
    }

    override fun onQueryTextChange(newText: String?) = true
    override val recyclerAdapter: BaseRecyclerAdapter<SearchItemModel> = SearchAdapter()
}

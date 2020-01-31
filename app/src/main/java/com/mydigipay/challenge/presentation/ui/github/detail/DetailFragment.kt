package com.mydigipay.challenge.presentation.ui.github.detail

import android.util.Log
import com.github.mohammadsianaki.core.model.Resource
import com.github.mohammadsianaki.core.model.ResourcesState
import com.mydigipay.challenge.presentation.core.BaseRecyclerAdapter
import com.mydigipay.challenge.presentation.core.ListFragment
import com.mydigipay.challenge.presentation.model.CommitItemModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DetailFragment : ListFragment<CommitItemModel, DetailFragmentArgs, DetailViewModel>() {

    override val recyclerAdapter: BaseRecyclerAdapter<CommitItemModel> = DetailAdapter()

    override fun renderUI(resource: Resource<List<CommitItemModel>>?) {
        resource?.let {
            when (resource.resourcesState) {
                ResourcesState.Loading -> {
                    hideRecyclerView()
                    hideEmptyView()
                    hideErrorView()
                    showLoading()
                    Log.d("<<<DetailFragment>>>", "loading")
                }
                ResourcesState.Success -> {
                    hideLoading()
                    hideEmptyView()
                    hideErrorView()
                    showRecyclerView()
                    handleData(it.data)
                    Log.d("<<<DetailFragment>>>", "success : ${it.data?.size}")
                }
                ResourcesState.Failure -> {
                    hideLoading()
                    hideRecyclerView()
                    hideEmptyView()
                    showErrorView(resource.failure)
                    Log.d("<<<DetailFragment>>>", "failure : ${it.failure}")
                }
            }
        }
    }

    override fun handleData(data: List<CommitItemModel>?) {
        if (data == null || data.isEmpty()) {
            hideLoading()
            hideRecyclerView()
            hideErrorView()
            showEmptyView()
            Log.d("<<<debug:DetailFrag>>>>", "null or empty")
        } else {
            Log.d("<<<debug:DetailFrag>>>>", "data is full")
            recyclerAdapter.items = data
        }
    }

    override fun makeViewModel(): DetailViewModel = getViewModel()
}

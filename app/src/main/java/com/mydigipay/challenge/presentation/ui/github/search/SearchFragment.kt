package com.mydigipay.challenge.presentation.ui.github.search

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.github.mohammadsianaki.core.extenstion.observe
import com.github.mohammadsianaki.core.model.Resource
import com.github.mohammadsianaki.core.model.ResourcesState
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.presentation.model.SearchItemModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()
        observe(viewModel.getLiveData()) { resource -> renderUI(resource) }
    }

    private fun renderUI(resource: Resource<List<SearchItemModel>>?) {
        resource?.let {
            when (resource.resourcesState) {
                ResourcesState.Success -> {
                    Log.d("<<<SearchFragment>>>", "success : ${it.data?.size}")
                }
                ResourcesState.Failure -> {
                    Log.d("<<<SearchFragment>>>", "failure : ${it.failure}")
                }
                ResourcesState.Loading -> {
                    Log.d("<<<SearchFragment>>>", "loading")
                }
            }
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
}

package com.mydigipay.challenge.presentation.github.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.widget.textChanges
import com.mydigipay.challenge.app.ViewModelProviderFactory
import com.mydigipay.challenge.app.component
import com.mydigipay.challenge.presentation.github.R
import com.mydigipay.challenge.presentation.github.SearchToCommitViewModel
import com.mydigipay.challenge.presentation.model.UserItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchFragment : Fragment() {

    private val searchInputDelay = 1000L
    private val searchInputDelayTimeUnit = TimeUnit.MILLISECONDS
    private lateinit var compositeDisposable: CompositeDisposable
    private var lastVisibleRepo = 0
    private val stateBundlePositionKey = "POSITION_KEY"
    private val stateBundleSearchQueryKey = "QUERY_KEY"
    private lateinit var adapter: RepoAdapter
    private var searchQuery = ""
    private val repoSelectionViewModel: SearchToCommitViewModel by activityViewModels()

    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var viewModel: SearchFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        compositeDisposable = CompositeDisposable()
        component.viewModelProviderFactory.create().inject(this)
        viewModel = ViewModelProvider(this, factory)[SearchFragmentViewModel::class.java]

        initViewInteraction(savedInstanceState)
        initDataInteraction()
    }

    private fun initDataInteraction() {

        viewModel.getState()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                handleState(it)
            }.let {
                compositeDisposable.add(it)
            }

    }

    private fun handleState(state: SearchFragmentState) {
        when (state) {
            is SearchFragmentState.Error -> {
                loading.hide()
                repoRv.visibility = GONE
                errorTv.visibility = VISIBLE
                tryAgainBtn.visibility = VISIBLE
                errorTv.text = getString(R.string.netwrok_error)
            }
            is SearchFragmentState.SearchedRepository -> {
                loading.hide()
                adapter.submitList(state.repositories)
                errorTv.visibility = GONE
                tryAgainBtn.visibility = GONE
                repoRv.visibility = VISIBLE
                repoRv.scrollToPosition(lastVisibleRepo)
            }
            is SearchFragmentState.Loading -> {
                loading.show()
                repoRv.visibility = GONE
                errorTv.visibility = GONE
                tryAgainBtn.visibility = GONE
            }
            is SearchFragmentState.NoRepoFound -> {
                loading.hide()
                repoRv.visibility = GONE
                errorTv.visibility = VISIBLE
                tryAgainBtn.visibility = GONE
                errorTv.text = getString(R.string.no_repo_found)
            }
            is SearchFragmentState.EmptyQuery -> {
                loading.hide()
                repoRv.visibility = GONE
                errorTv.visibility = VISIBLE
                tryAgainBtn.visibility = GONE
                errorTv.text = getString(R.string.empty_search)
            }
        }
    }

    private fun initViewInteraction(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            lastVisibleRepo = it.getInt(stateBundlePositionKey)
            searchQuery = it.getString(stateBundleSearchQueryKey) ?: ""
        }
        initRecyclerView()
        initSearchBar()
        tryAgainBtn.setOnClickListener {
            if (searchQuery.isNotEmpty() && searchQuery.isNotBlank())
                viewModel.searchRepository(searchQuery)
        }
        userAvatarImg.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_userProfileFragment)
        }
    }

    private fun initRecyclerView() {
        adapter =
            RepoAdapter()
        adapter.selectedRepo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                repoSelectionViewModel.owner = it.repoOwnerItem?.login ?: ""
                repoSelectionViewModel.repo = it.name
                findNavController().navigate(R.id.action_searchFragment_to_commitFragment)
            }.let {
                compositeDisposable.add(it)
            }
        repoRv.layoutManager = LinearLayoutManager(requireContext())
        repoRv.adapter = adapter
        repoRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        lastVisibleRepo =
                            (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    }
                }
            }
        })
    }


    private fun initSearchBar() {
        searchEdt
            .textChanges()
            .skipInitialValue()
            .debounce(searchInputDelay, searchInputDelayTimeUnit)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (!it.toString().equals(searchQuery)) {
                    searchQuery = it.toString()
                    viewModel.searchRepository(searchQuery)
                }
            }, {
            }).let {
                compositeDisposable.add(it)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(stateBundlePositionKey, lastVisibleRepo)
        outState.putString(stateBundleSearchQueryKey, searchQuery)
        super.onSaveInstanceState(outState)
    }
}
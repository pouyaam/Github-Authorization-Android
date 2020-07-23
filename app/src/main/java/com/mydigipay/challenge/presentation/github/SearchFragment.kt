package com.mydigipay.challenge.presentation.github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.widget.textChanges
import com.mydigipay.challenge.app.component
import com.mydigipay.challenge.presentation.github.databinding.FragmentSearchBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchInputDelay = 300L
    private val searchInputDelayTimeUnit = TimeUnit.MILLISECONDS
    private lateinit var compositeDisposable: CompositeDisposable
    private var firstVisibleRepo = 0
    private val stateBundlePositionKey = "POSITION_KEY"
    private lateinit var adapter: RepoAdapter
    private lateinit var repoRv: RecyclerView

    @Inject
    lateinit var viewModel: SearchFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        compositeDisposable = CompositeDisposable()
        component.viewModelProviderFactory.create().inject(this)
        initViewIntraction(savedInstanceState)
        initDataIntraction(savedInstanceState)
    }

    private fun initDataIntraction(savedInstanceState: Bundle?) {

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
            }
            is SearchFragmentState.SearchedRepository -> {
                loading.hide()
                adapter.submitList(state.repositories)
            }
            is SearchFragmentState.Loading -> {
                loading.show()
            }
        }
    }

    private fun initViewIntraction(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            firstVisibleRepo = it.getInt(stateBundlePositionKey)
        }
        initSearchBar()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = RepoAdapter()
        adapter.selectedRepo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }).let {
                compositeDisposable.add(it)
            }
        repoRv = binding.repoRv
        repoRv.layoutManager = LinearLayoutManager(requireContext())
        repoRv.adapter = adapter
    }

    private fun getFirstVisibleItemPosition(): Int {
        (repoRv.layoutManager as? GridLayoutManager)
            ?.let {
                return it.findFirstCompletelyVisibleItemPosition()
            }
        return 0
    }

    private fun initSearchBar() {
        binding.searchEdt
            .textChanges()
            .skipInitialValue()
            .debounce(searchInputDelay, searchInputDelayTimeUnit)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                viewModel.searchRepository(it.toString())
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
        outState.putInt(stateBundlePositionKey, firstVisibleRepo)
        super.onSaveInstanceState(outState)

    }
}
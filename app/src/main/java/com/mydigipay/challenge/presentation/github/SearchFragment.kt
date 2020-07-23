package com.mydigipay.challenge.presentation.github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding3.widget.textChanges
import com.mydigipay.challenge.app.component
import com.mydigipay.challenge.presentation.github.databinding.FragmentSearchBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchInputDelay = 300L
    private val searchInputDelayTimeUnit = TimeUnit.MILLISECONDS
    private lateinit var compositeDisposable: CompositeDisposable

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
    }

    private fun initViewIntraction(savedInstanceState: Bundle?) {
        initSearchBar()
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
}
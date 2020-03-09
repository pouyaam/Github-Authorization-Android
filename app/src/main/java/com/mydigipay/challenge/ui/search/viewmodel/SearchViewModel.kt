package com.mydigipay.challenge.ui.search.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import androidx.navigation.fragment.FragmentNavigator
import com.mydigipay.challenge.core.BaseFragment
import com.mydigipay.challenge.core.RxNavBaseViewModel
import com.mydigipay.challenge.dataaccess.model.ResponseProjectItem
import com.mydigipay.challenge.github.BR
import com.mydigipay.challenge.ui.search.model.SearchModel
import com.mydigipay.challenge.ui.search.view.SearchFragmentDirections
import com.mydigipay.challenge.ui.search.view.SearchItemAdapter
import com.mydigipay.challenge.util.EndlessRecyclerViewScrollListener
import com.mydigipay.challenge.util.go
import com.mydigipay.challenge.util.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SearchViewModel(
    fragment: BaseFragment,
    private val model: SearchModel,
    private val compositeDisposable: CompositeDisposable
) : RxNavBaseViewModel(compositeDisposable) {

    @Bindable
    var showSearchLoading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showSearchLoading)
        }

    @Bindable
    var showFetchLoading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showFetchLoading)
        }

    var word: String = ""
        set(value) {
            field = value
            wordPublisher.onNext(value)
        }

    private val wordPublisher = PublishSubject.create<String>()
    private var projectItems = MutableLiveData<ArrayList<ResponseProjectItem>>()


    private val openDetail = fun(item: ResponseProjectItem, extras: FragmentNavigator.Extras) {
        navigator.go(SearchFragmentDirections.actionSearchFragmentToDetailFragment(item), extras)
    }
    val adapter = SearchItemAdapter(openDetail)

    val nextPageListener = fun(page: Int, scrollListener: EndlessRecyclerViewScrollListener) {
        showFetchLoading = true
        compositeDisposable.add(
            model.search(word, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        showFetchLoading = false
                        it.remoteSearchItemEntities?.let { items ->
                            projectItems += items
                        }
                    }, {
                        showFetchLoading = false
                        scrollListener.failed()
                        //todo
                        it.printStackTrace()
                    }
                )
        )
    }

    fun openProfile() {
        navigator go SearchFragmentDirections.actionSearchFragmentToProfileFragment()
    }

    init {
        subscribeWordPublisher()
        projectItems.observe(fragment) { items ->
            adapter.items = items.toMutableList()
        }
    }

    private fun subscribeWordPublisher() {
        compositeDisposable.add(
            wordPublisher
                .doOnNext {
                    showSearchLoading = true
                    projectItems.value = arrayListOf()
                }
                .filter { it.length > 2 }
                .debounce(700, TimeUnit.MILLISECONDS)
                .flatMap { model.search(it, 1).toObservable() }
                .retry()
                .doOnNext {
                    showSearchLoading = false
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        it.remoteSearchItemEntities?.let { items ->
                            projectItems.value = arrayListOf<ResponseProjectItem>().apply {
                                addAll(items)
                            }
                        }
                    }, {
                        //todo
                        it.printStackTrace()
                    }
                )
        )
    }
}
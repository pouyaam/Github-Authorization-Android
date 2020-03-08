package com.mydigipay.challenge.ui.search.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import com.mydigipay.challenge.core.BaseFragment
import com.mydigipay.challenge.core.RxNavBaseViewModel
import com.mydigipay.challenge.dataaccess.model.ResponseProject
import com.mydigipay.challenge.dataaccess.model.ResponseProjectItem
import com.mydigipay.challenge.github.BR
import com.mydigipay.challenge.ui.search.model.SearchModel
import com.mydigipay.challenge.ui.search.view.SearchItemAdapter
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
    var showLoading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showLoading)
        }

    var word: String = ""
        set(value) {
            field = value
            wordPublisher.onNext(value)
        }

    private var wordPublisher = PublishSubject.create<String>()
    private var projectItems = MutableLiveData<List<ResponseProjectItem>>()

    val adapter = SearchItemAdapter()

    val nextPageListener = fun(page: Int) {
        compositeDisposable.add(
            model.search(word, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriberComplete(), subscriberError())
        )
    }

    init {
        subscribeWordPublisher()
        projectItems.observe(fragment) { items ->
            adapter.items = items.toMutableList()
        }
    }

    override fun bindNavigator() {
        super.bindNavigator()
        adapter.navigator = navigator
    }

    private fun subscribeWordPublisher() {
        compositeDisposable.add(
            wordPublisher
                .doOnNext { beforeSendRequest() }
                .filter { it.length > 2 }
                .debounce(700, TimeUnit.MILLISECONDS)
                .flatMap { model.search(it, 1).toObservable() }
                .doOnNext { afterSendRequest() }
                .retry()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriberComplete(), subscriberError())
        )
    }

    private fun beforeSendRequest() {
        showLoading = true
        projectItems.value = mutableListOf()
    }

    private fun afterSendRequest() {
        showLoading = false
    }

    private fun subscriberComplete(): (t: ResponseProject) -> Unit {
        return {
            it.remoteSearchItemEntities?.let { list ->
                projectItems.value = list
            }
        }
    }

    private fun subscriberError(): (t: Throwable) -> Unit {
        return {
            afterSendRequest()
            it.printStackTrace()
        }
    }


}
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
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit


class SearchViewModel(
    private val fragment: BaseFragment,
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

    var page = 1

    private var wordPublisher: Subject<String> = PublishSubject.create()
    private var projectItems: MutableLiveData<List<ResponseProjectItem>> = MutableLiveData()

    val adapter: SearchItemAdapter = SearchItemAdapter(navigator)

    init {
        subscribeWordPublisher()
        projectItems.observe(fragment) { items ->
            adapter.data = items.toMutableList()
        }
    }

    private fun subscribeWordPublisher() {
        compositeDisposable.add(
            wordPublisher
                .doOnNext { beforeSendRequest() }
                .filter { it.length > 2 }
                .debounce(700, TimeUnit.MILLISECONDS)
                .flatMap { model.search(it, page).toObservable() }
                .doOnNext { afterSendRequest() }
                .retry()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriberComplete(), subscriberError())
        )
    }

    private fun beforeSendRequest() {
        page = 1
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
                page++
            }
        }
    }

    private fun subscriberError(): (t: Throwable) -> Unit {
        return {
//            it.printStackTrace()
        }
    }


}
package com.mydigipay.challenge.ui.detail.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import com.mydigipay.challenge.core.BaseFragment
import com.mydigipay.challenge.core.RxNavBaseViewModel
import com.mydigipay.challenge.dataaccess.model.ResponseCommitItem
import com.mydigipay.challenge.dataaccess.model.ResponseProjectItem
import com.mydigipay.challenge.github.BR
import com.mydigipay.challenge.ui.detail.model.DetailModel
import com.mydigipay.challenge.ui.detail.view.DetailItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel(
    private val fragment: BaseFragment,
    private val model: DetailModel,
    private val compositeDisposable: CompositeDisposable
) : RxNavBaseViewModel(compositeDisposable) {

    @Bindable
    var showLoading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showLoading)
        }

    @Bindable
    var item: ResponseProjectItem? = null
        set(value) {
            field = value
            value?.let {
                getCommit(it)
            }
            notifyChange()
        }

    private var commitItems = MutableLiveData<List<ResponseCommitItem>>()
    val adapter = DetailItemAdapter()

    init {
        commitItems.observe(fragment) { items ->
            adapter.data = items.toMutableList()
        }
    }

    fun back() = navigator.popBackStack()

    private fun getCommit(item: ResponseProjectItem) {
        compositeDisposable.add(
            model.getAll(
                    item.remoteOwnerEntity?.login ?: "",
                    item.name ?: ""
                )
                .doOnSubscribe { showLoading = true }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        showLoading = false
                        commitItems.value = it
                    },
                    {
                        it.printStackTrace()
                        navigator.popBackStack()
                    }
                )
        )
    }
}
package com.mydigipay.challenge.ui.search.viewmodel

import com.mydigipay.challenge.core.BaseViewModel
import com.mydigipay.challenge.dataaccess.model.ResponseProjectItem

class SearchItemViewModel(
    val item: ResponseProjectItem,
    private val openDetail: (item: ResponseProjectItem) -> Unit
) : BaseViewModel() {

    fun showCommit() {
        openDetail(item)
    }

}

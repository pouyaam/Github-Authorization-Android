package com.mydigipay.challenge.ui.search.viewmodel

import androidx.navigation.NavController
import com.mydigipay.challenge.core.BaseViewModel
import com.mydigipay.challenge.dataaccess.model.ResponseProjectItem
import com.mydigipay.challenge.ui.search.view.SearchFragmentDirections
import com.mydigipay.challenge.util.go

class SearchItemViewModel(
    val item: ResponseProjectItem,
    private val navigator: NavController
) : BaseViewModel() {

    fun showCommit() {
        navigator go SearchFragmentDirections.actionSearchFragmentToDetailFragment(item)
    }

}

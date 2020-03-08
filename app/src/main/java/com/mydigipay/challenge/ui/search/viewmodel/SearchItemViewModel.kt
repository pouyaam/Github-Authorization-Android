package com.mydigipay.challenge.ui.search.viewmodel

import android.view.View
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.mydigipay.challenge.core.BaseViewModel
import com.mydigipay.challenge.dataaccess.model.ResponseProjectItem

class SearchItemViewModel(
    val item: ResponseProjectItem,
    private val openDetail: (item: ResponseProjectItem, extras: FragmentNavigator.Extras) -> Unit
) : BaseViewModel() {

    fun showCommit(view: View, name: String) {
        val extras = FragmentNavigatorExtras(
            view to name
        )
        openDetail(item, extras)
    }

}

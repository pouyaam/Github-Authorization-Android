package com.mydigipay.challenge.ui.search.viewmodel

import androidx.navigation.NavController
import com.mydigipay.challenge.core.BaseViewModel
import com.mydigipay.challenge.dataaccess.model.ResponseProjectItem

class SearchItemViewModel(
    val item: ResponseProjectItem,
    private val navigator: NavController?
) : BaseViewModel()
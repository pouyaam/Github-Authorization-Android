package com.mydigipay.challenge.presentation.ui.github.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mydigipay.challenge.github.databinding.ItemRepositoryBinding
import com.mydigipay.challenge.presentation.core.BaseRecyclerAdapter
import com.mydigipay.challenge.presentation.core.BaseViewHolder
import com.mydigipay.challenge.presentation.model.SearchItemModel

class SearchAdapter : BaseRecyclerAdapter<SearchItemModel>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<SearchItemModel> = SearchViewHolder(
        ItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        this.onRecyclerItemClickListener
    )
}
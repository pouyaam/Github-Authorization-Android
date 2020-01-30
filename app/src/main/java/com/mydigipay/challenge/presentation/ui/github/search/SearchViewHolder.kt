package com.mydigipay.challenge.presentation.ui.github.search

import com.mydigipay.challenge.github.BR
import com.mydigipay.challenge.github.databinding.ItemRepositoryBinding
import com.mydigipay.challenge.presentation.core.BaseViewHolder
import com.mydigipay.challenge.presentation.core.OnRecyclerItemClickListener
import com.mydigipay.challenge.presentation.model.SearchItemModel

class SearchViewHolder(
    private val binding: ItemRepositoryBinding,
    onRecyclerItemClickListener: OnRecyclerItemClickListener<SearchItemModel>?
) : BaseViewHolder<SearchItemModel>(binding, onRecyclerItemClickListener) {

    override fun bind(itemData: SearchItemModel) {
        binding.setVariable(BR.itemData, itemData)
        binding.setVariable(BR.itemClickListener, itemClickListener)
    }
}
package com.mydigipay.challenge.presentation.core

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mydigipay.challenge.presentation.model.SearchItemModel

abstract class BaseViewHolder<T : ItemModel>(
    binding: ViewDataBinding,
    protected val itemClickListener: OnRecyclerItemClickListener<SearchItemModel>?
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(itemData: T)
}
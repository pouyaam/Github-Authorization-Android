package com.mydigipay.challenge.presentation.core

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T : ItemModel>(
    binding: ViewDataBinding,
    protected val itemClickListener: OnRecyclerItemClickListener<T>
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(itemData: T)
}
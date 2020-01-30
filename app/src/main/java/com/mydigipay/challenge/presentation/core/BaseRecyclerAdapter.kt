package com.mydigipay.challenge.presentation.core

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T : ItemModel> : RecyclerView.Adapter<BaseViewHolder<T>>() {
    var onRecyclerItemClickListener: OnRecyclerItemClickListener<T>? = null
    var items: List<T> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) =
        holder.bind(items[position])

    abstract fun makeViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T>
}
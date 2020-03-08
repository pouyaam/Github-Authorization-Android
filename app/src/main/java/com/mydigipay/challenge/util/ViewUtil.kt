package com.mydigipay.challenge.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["loadAdapter"])
fun RecyclerView.loadAdapter(itemAdapter: RecyclerView.Adapter<*>?) {
    itemAdapter?.let {
        this.adapter = it
    }
}

@BindingAdapter(value = ["scrollListener"])
fun RecyclerView.scrollListener(listener: RecyclerView.OnScrollListener) {
    this.addOnScrollListener(listener)
}

@BindingAdapter(value = ["nextPageListener"])
fun RecyclerView.nextPageListener(nextPage: (page: Int) -> Unit) {
    layoutManager?.let { layoutManager ->
        this.addOnScrollListener(
            object : EndlessRecyclerViewScrollListener(layoutManager) {
                override fun onLoadMore(page: Int) {
                    nextPage(page)
                }
            }
        )
    }
}
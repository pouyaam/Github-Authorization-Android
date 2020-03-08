package com.mydigipay.challenge.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["loadAdapter"])
fun RecyclerView.loadAdapter(itemAdapter: RecyclerView.Adapter<*>?) {
    itemAdapter?.let {
        adapter = it
    }
}

@BindingAdapter(value = ["nextPageListener"])
fun RecyclerView.nextPageListener(nextPage: (page: Int, scrollListener: EndlessRecyclerViewScrollListener) -> Unit) {
    layoutManager?.let { layoutManager ->
        addOnScrollListener(
            object : EndlessRecyclerViewScrollListener(layoutManager) {
                override fun onLoadMore(page: Int) {
                    nextPage(page, this)
                }
            }
        )
    }
}
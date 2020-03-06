package com.mydigipay.challenge.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["loadAdapter"])
fun RecyclerView.loadAdapter(itemAdapter: RecyclerView.Adapter<*>?) {
    itemAdapter?.let {
        this.layoutManager = LinearLayoutManager(context)
        this.adapter = it
    }
}

@BindingAdapter(value = ["scrollListener"])
fun RecyclerView.scrollListener(listener: RecyclerView.OnScrollListener) {
    this.addOnScrollListener(listener)
}
package com.mydigipay.challenge.util

import android.view.View
import android.widget.ImageView
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

@BindingAdapter(value = ["loadUrl", "hideOnEmptyUrl"], requireAll = false)
fun ImageView.loadUrl(
    url: String?,
    hideOnEmptyUrl: Boolean = false
) {
    if (url != null && url.isNotEmpty()) {
        loadImage(url, this)
    } else if (hideOnEmptyUrl) {
        this.visibility = View.GONE
    }
}
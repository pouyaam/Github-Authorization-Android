package com.mydigipay.challenge.util

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
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

@BindingAdapter(value = ["loadUrl", "hideOnEmptyUrl", "circleTransform"], requireAll = false)
fun ImageView.loadUrl(
    url: String?,
    hideOnEmptyUrl: Boolean = false,
    circleTransform: Boolean = true
) {
    if (url != null && url.isNotEmpty()) {
        loadImage(
            url = url,
            imageView = this,
            circleTransform = circleTransform
        )
    } else if (hideOnEmptyUrl) {
        this.visibility = View.GONE
    }
}


@BindingAdapter(value = ["grayscale"])
fun ImageView.grayscale(grayscale: Boolean) {
    if (grayscale) {
        colorFilter = ColorMatrixColorFilter(
            ColorMatrix().apply {
                setSaturation(0f)
            }
        )
        imageAlpha = 128
    }
}
package com.mydigipay.challenge.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseAdapter


@BindingAdapter("visibleGone")
fun goneUnless(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, url: String) {
    try {
        Glide.with(imageView)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.github_logo)
            .into(imageView)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("listItem")
fun <T> setListItem(recyclerView: RecyclerView, data: MutableList<T>?) {
    (recyclerView.adapter as BaseAdapter<T, *>).submitList(data)
}
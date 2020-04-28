package com.mydigipay.challenge.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mydigipay.challenge.R
import com.mydigipay.challenge.base.BaseAdapter


@BindingAdapter("goneUnless")
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

@BindingAdapter("imageVector")
fun bindImage(view: View, resId: Int) {
    (view as AppCompatImageView).setImageResource(resId)
}

@BindingAdapter("number")
fun <T : Number> bindText(textView: TextView, number: T) {
    // TODO: 4/28/20 make it readable
    textView.text = "$number"
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("data")
fun <T> setListItem(recyclerView: RecyclerView, data: MutableList<T>?) {
    (recyclerView.adapter as BaseAdapter<T, *>).submitList(data)
}
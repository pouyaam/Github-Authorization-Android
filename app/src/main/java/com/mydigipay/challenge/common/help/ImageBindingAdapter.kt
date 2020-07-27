package com.mydigipay.challenge.common.help

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mydigipay.challenge.R

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("thumbnailImgUrl")
    fun setThumbnailImgUrl(imageView: ImageView, thumbnailImgUrl: String?) {
        Glide.with(imageView.context)
            .load("https://yusmle.com/YUSMLE/items/pics/thumbs/$thumbnailImgUrl")
            .placeholder(R.mipmap.ic_launcher_round)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("imgUrl")
    fun setImgUrl(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load("https://yusmle.com/YUSMLE/items/pics/$imageUrl")
            .placeholder(R.mipmap.ic_launcher_round)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("visibleIf")
    fun changeVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}

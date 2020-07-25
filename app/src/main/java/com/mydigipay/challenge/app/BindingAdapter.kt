package com.mydigipay.challenge.app

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mydigipay.challenge.presentation.github.R

class BindingAdapter {

    companion object {

        val movieImagePlaceHolder = R.drawable.ic_account

        @BindingAdapter("android:imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String?) {
            imageUrl?.let {
                Glide.with(view)
                    .setDefaultRequestOptions(
                        RequestOptions().circleCrop()
                    )
                    .load(imageUrl)
                    .placeholder(movieImagePlaceHolder)
                    .error(movieImagePlaceHolder)
                    .into(view)
            }
        }
    }


}
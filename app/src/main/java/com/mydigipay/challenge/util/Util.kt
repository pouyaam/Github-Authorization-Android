package com.mydigipay.challenge.util

import android.widget.ImageView
import com.mydigipay.challenge.github.R
import com.squareup.picasso.Picasso


fun loadImage(
    url: String,
    imageView: ImageView,
    placeholder: Int = R.color.transparent,
    circleTransform: Boolean = true
) {
    Picasso.get()
        .load(url)
        .placeholder(placeholder)
        .apply {
            if (circleTransform)
                transform(CircleTransform())
        }.into(imageView)
}
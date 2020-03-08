package com.mydigipay.challenge.util

import android.widget.ImageView
import com.mydigipay.challenge.github.R
import com.squareup.picasso.Picasso


fun loadImage(url: String, iv: ImageView, placeholder: Int = R.color.transparent) {
    Picasso.get()
        .load(url)
        .placeholder(placeholder)
        .transform(CircleTransform())
        .into(iv)
}
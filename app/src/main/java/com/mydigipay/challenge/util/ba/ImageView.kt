package com.mydigipay.challenge.util.ba

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.CircleCropTransformation

@BindingAdapter("image", "placeholder", "isCircle", requireAll = false)
fun ImageView.imageUrl(url: String?, placeholder: Drawable?, isCircle: Boolean?) {
    load(url) {
        crossfade(true)
        placeholder(placeholder)
        if (isCircle == true) transformations(CircleCropTransformation())
    }
}
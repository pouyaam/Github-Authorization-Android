package com.mydigipay.challenge.util.ba

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.Transformation

@BindingAdapter("image", "placeholder", "isCircle", "hasBlur", "blurRadius", requireAll = false)
fun ImageView.loadImage(url: String?, placeholder: Drawable?, isCircle: Boolean?, hasBlur: Boolean?, blurRadius: Float?) {
    val transformations = mutableListOf<Transformation>()
    if (isCircle == true) transformations.add(CircleCropTransformation())
    if (hasBlur == true) transformations.add(BlurTransformation(context, blurRadius ?: 10f))
    load(url) {
        crossfade(true)
        placeholder(placeholder)
        this.transformations(transformations)
    }
}

@BindingAdapter("image", "placeholder", "isCircle", "hasBlur", "blurRadius", requireAll = false)
fun ImageView.loadImage(drawable: Drawable, placeholder: Drawable?, isCircle: Boolean?, hasBlur: Boolean?, blurRadius: Float?) {
    val transformations = mutableListOf<Transformation>()
    if (isCircle == true) transformations.add(CircleCropTransformation())
    if (hasBlur == true) transformations.add(BlurTransformation(context, blurRadius ?: 10f))
    load(drawable) {
        crossfade(true)
        placeholder(placeholder)
        this.transformations(transformations)
    }
}

@BindingAdapter("srcCompatRes")
fun ImageView.setSrcCompatRes(@DrawableRes drawable: Int) {
    setImageResource(drawable)
}
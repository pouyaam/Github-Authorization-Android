package com.mydigipay.challenge.utils.ktx

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.mydigipay.challenge.app.GlideApp


fun ImageView.load(
    url: String?,
    placeholder: Drawable? = null
) {
    val request =
        GlideApp.with(this)
            .load(url)

    request.apply {
        if (placeholder != null) this.placeholder(placeholder)
    }.into(this)
}

fun ImageView.loadRounded(
    url: String?,
    radius: Int,
    @DrawableRes placeholder: Int? = null,
    centerCrop: Boolean = false
) {

    val request =
        GlideApp.with(this)
            .load(url)

    request.apply {

        if (placeholder != null) this.placeholder(placeholder)
        transform(
            if (centerCrop) MultiTransformation(CenterCrop(), RoundedCorners(radius))
            else RoundedCorners(radius)
        )
    }.into(this)
}

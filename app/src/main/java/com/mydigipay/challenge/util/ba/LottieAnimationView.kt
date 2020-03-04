package com.mydigipay.challenge.util.ba

import androidx.annotation.RawRes
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView

@BindingAdapter("lottie_rawRes")
fun LottieAnimationView.setAnimationRawRes(@RawRes rawRes: Int) {
    setAnimation(rawRes)
    playAnimation()
}
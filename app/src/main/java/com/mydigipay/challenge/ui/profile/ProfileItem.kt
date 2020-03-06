package com.mydigipay.challenge.ui.profile

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ProfileItem {
    val id: Int
}

data class StringItem(
    override val id: Int,
    @DrawableRes val icon : Int,
    @StringRes val title: Int,
    val value: String
): ProfileItem
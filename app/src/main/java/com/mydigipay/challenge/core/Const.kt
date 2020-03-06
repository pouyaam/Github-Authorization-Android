package com.mydigipay.challenge.core

import com.mydigipay.challenge.github.BuildConfig

object Const {
    object Github {
        const val CLIENT_ID = BuildConfig.GITHUB_CLIENT_ID
        const val CLIENT_SECRET = BuildConfig.GITHUB_CLIENT_SECRET
        const val REDIRECT_URI = BuildConfig.GITHUB_REDIRECT_URI
    }
}
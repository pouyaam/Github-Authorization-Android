package com.mydigipay.challenge.utils

import com.mydigipay.challenge.app.App

const val GITHUB_CODE_URL =
    "https://github.com/login/oauth/authorize?client_id=%s&redirect_uri=${App.REDIRECT_URI}&scope=repo user&state=0"
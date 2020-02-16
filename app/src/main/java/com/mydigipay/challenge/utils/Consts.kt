package com.mydigipay.challenge.utils

import com.mydigipay.challenge.app.App

const val GITHUB_CODE_URL="https://github.com/login/oauth/authorize?client_id=${App.CLIENT_ID}&redirect_uri=${App.REDIRECT_URI}&scope=repo user&state=0"
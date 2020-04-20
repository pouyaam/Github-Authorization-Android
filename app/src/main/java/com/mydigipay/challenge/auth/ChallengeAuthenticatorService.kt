package com.mydigipay.challenge.auth

import android.app.Service
import android.content.Intent
import android.os.IBinder

class ChallengeAuthenticatorService : Service() {
    private lateinit var authenticator: ChallengeAuthenticator
    override fun onBind(intent: Intent): IBinder? {
        authenticator = ChallengeAuthenticator(this)
        return authenticator.iBinder
    }
}
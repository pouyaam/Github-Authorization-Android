package com.mydigipay.challenge.repository.token

import android.media.session.MediaSession
import androidx.lifecycle.LiveData
import com.mydigipay.challenge.network.repository.Repository
import com.mydigipay.challenge.ui.home.LoginResult

interface LoginRepository {
    var code: String?
    suspend fun saveToken(token: String)
    suspend fun getRepositories(code: String): LoginResult
    suspend fun getToken(code: String): Boolean
}
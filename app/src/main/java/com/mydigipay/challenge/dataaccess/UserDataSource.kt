package com.mydigipay.challenge.dataaccess

import com.mydigipay.challenge.dataaccess.model.UserResponse
import io.reactivex.Single

interface UserDataSource {

    fun getUser(token: String): Single<UserResponse>

}
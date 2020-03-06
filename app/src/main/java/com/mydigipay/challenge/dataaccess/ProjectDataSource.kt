package com.mydigipay.challenge.dataaccess

import com.mydigipay.challenge.dataaccess.model.ResponseProject
import io.reactivex.Single

interface ProjectDataSource {

    fun search(word: String, page: Int): Single<ResponseProject>

}
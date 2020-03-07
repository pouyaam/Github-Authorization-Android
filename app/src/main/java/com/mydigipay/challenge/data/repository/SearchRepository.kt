package com.mydigipay.challenge.data.repository

import com.mydigipay.challenge.data.network.ApiResult
import com.mydigipay.challenge.data.pojo.Order
import com.mydigipay.challenge.data.pojo.Sort
import com.mydigipay.challenge.data.pojo.Repository

interface SearchRepository {

    suspend fun searchRepositories(
        query: String,
        page: Int,
        perPage: Int = 10,
        sort: Sort = Sort.STARS,
        order: Order = Order.DESC
    ): ApiResult<List<Repository>>
}
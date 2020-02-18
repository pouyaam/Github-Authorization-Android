package com.mydigipay.challenge.ui.home

import com.mydigipay.challenge.network.model.repository.Repository

interface HomeRepository {
    suspend fun getRepositories(): List<Repository>
}
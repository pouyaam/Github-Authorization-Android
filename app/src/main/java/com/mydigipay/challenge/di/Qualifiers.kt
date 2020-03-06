package com.mydigipay.challenge.di

import org.koin.core.qualifier.named

object Qualifiers {
    const val tokenDataSource = "TokenDataSource"
    const val projectDataSource = "ProjectDataSource"
    fun getNamedDependencyRepository(domain: String) = named("$domain Repository")
    fun getNamedDependencyRemote(domain: String) = named("$domain Remote")
    fun getNamedDependencyLocal(domain: String) = named("$domain Local")
    fun getNamedDependencyCache(domain: String) = named("$domain Cache")
}
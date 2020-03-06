package com.mydigipay.challenge.di

import org.koin.core.qualifier.named

object Qualifiers {
    val NAMED_DEPENDENCY_REPOSITORY = named("REPOSITORY")
    val NAMED_DEPENDENCY_REMOTE = named("REMOTE")
    val NAMED_DEPENDENCY_LOCAL = named("LOCAL")
    val NAMED_DEPENDENCY_CACHE = named("CACHE")
}
package com.mydigipay.challenge.domain.interactors

abstract class Usecase<out T> {
    abstract suspend fun execute(data: Map<String, Any>? = null): T
}
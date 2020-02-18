package com.mydigipay.challenge.utils

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
@FlowPreview
class EventBus {
    private val channel = BroadcastChannel<Any>(1)

    fun send(event: Any, context: CoroutineContext = Dispatchers.Default) {
        CoroutineScope(context).launch {
            channel.send(event)
        }
    }

    fun subscribe(): Flow<Any> = channel.asFlow()

    inline fun <reified T> subscribeToEvent() =
        subscribe().filter { it is T }.map { it as T }


    inline fun <reified T> collectEventOnMainThread(crossinline onNext: (T) -> Unit) =
        CoroutineScope(Dispatchers.Main).launch {
            subscribeToEvent<T>().collect { onNext(it) }
        }

    companion object {
        val instance = EventBus()
    }
}
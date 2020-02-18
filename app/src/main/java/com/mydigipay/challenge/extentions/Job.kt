package com.mydigipay.challenge.extentions

import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren

fun Job.cancleTree() {
    cancelChildren()
    cancel()
}

fun List<Job>.clearAllDisposables() {
    forEach {
        it.cancleTree()
    }
}
package com.mydigipay.challenge.infrastructure.utils

import io.reactivex.Scheduler

interface SchedulersProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}
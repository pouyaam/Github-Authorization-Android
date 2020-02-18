package com.mydigipay.challenge.base

import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.extentions.clearAllDisposables
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job

@FlowPreview
@ExperimentalCoroutinesApi
abstract class BaseViewModel : ViewModel() {

    private val disposableJobs by lazy { mutableListOf<Job>() }

    fun addToUnsubscribe(job: Job) {
        disposableJobs.add(job)
    }

    override fun onCleared() {
        disposableJobs.clearAllDisposables()
        super.onCleared()
    }


}
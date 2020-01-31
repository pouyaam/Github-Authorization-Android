package com.mydigipay.challenge.presentation.core.stub

import com.github.mohammadsianaki.core.model.ErrorHolder
import com.github.mohammadsianaki.core.model.Resource
import com.github.mohammadsianaki.core.model.ResourcesState
import com.mydigipay.challenge.presentation.core.ListViewModel

class StubListViewModel : ListViewModel<StubItemModel, StubParamters>() {

    override fun makeData(params: StubParamters) {
        when (params.type) {
            Type.SUCCESS -> success()
            Type.ERROR -> error()
            Type.LOAD -> waiting()
        }
    }

    private fun success() {
        liveData.value = Resource(ResourcesState.Success, emptyList())
    }

    private fun error() {
        liveData.value =
            Resource(ResourcesState.Failure, null, ErrorHolder.UnExpected("stub error"))
    }

    private fun waiting() {
        liveData.value = Resource(ResourcesState.Loading, null, null)
    }
}
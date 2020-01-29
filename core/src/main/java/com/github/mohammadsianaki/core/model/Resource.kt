package com.github.mohammadsianaki.core.model

data class Resource<out T>(
    val resourcesState: ResourcesState,
    val data: T? = null,
    val failure: ErrorHolder? = null
)


sealed class ResourcesState {
    object Success : ResourcesState()
    object Failure : ResourcesState()
    object Loading : ResourcesState()
}
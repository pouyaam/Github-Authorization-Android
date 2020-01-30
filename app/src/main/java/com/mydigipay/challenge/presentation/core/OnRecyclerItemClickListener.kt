package com.mydigipay.challenge.presentation.core

interface OnRecyclerItemClickListener<T> {
    fun onItemClicked(item: T)
}
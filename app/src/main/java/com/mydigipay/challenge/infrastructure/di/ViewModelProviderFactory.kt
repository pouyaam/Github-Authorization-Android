package com.mydigipay.challenge.infrastructure.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Mahdi Zare Tahghigh Doost on 5/10/2020.
 *
 * mahdiZTD@gmail.com
 */
class ViewModelProviderFactory<V : Any>(var viewModel: V) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModel.javaClass)) {
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
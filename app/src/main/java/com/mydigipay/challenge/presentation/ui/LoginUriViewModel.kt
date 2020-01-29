package com.mydigipay.challenge.presentation.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mohammadsianaki.core.model.Resource
import com.github.mohammadsianaki.core.model.ResourcesState
import com.github.mohammadsianaki.core.model.fold
import com.github.mohammadsianaki.core.toplevel.awaitIO
import com.mydigipay.challenge.domain.interactors.GetAccessTokenUsecase
import com.mydigipay.challenge.presentation.CLIENT_ID
import com.mydigipay.challenge.presentation.CLIENT_SECRET
import com.mydigipay.challenge.presentation.REDIRECT_URI
import com.mydigipay.challenge.presentation.model.AccessTokenModel
import com.mydigipay.challenge.presentation.model.AccessTokenRequestConfigs
import com.mydigipay.challenge.presentation.model.toAccessTokenModel
import kotlinx.coroutines.launch

class LoginUriViewModel(
    private val usecase: GetAccessTokenUsecase
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<AccessTokenModel>> = MutableLiveData()

    suspend fun fetchAccessToken(code: String) {
        liveData.value = Resource(ResourcesState.Loading)
        viewModelScope.launch {
            awaitIO {
                usecase.execute(
                    mapOf(
                        KEY_CONFIGS to AccessTokenRequestConfigs(
                            CLIENT_ID,
                            CLIENT_SECRET, code,
                            REDIRECT_URI, "0"
                        )
                    )
                )
            }.fold(
                ifSuccess = {
                    liveData.value = Resource(ResourcesState.Success, it.toAccessTokenModel())
                },
                ifFailure = {
                    it?.let {
                        Log.d("<<<", it.message)
                        liveData.value = Resource(ResourcesState.Failure, null, it)
                    }
                }
            )
        }
    }

    fun getLiveData(): LiveData<Resource<AccessTokenModel>> = liveData

    companion object {
        const val KEY_CONFIGS = "key-configs"
    }
}
package com.mydigipay.challenge.presentation.design

import android.app.Application
import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mydigipay.challenge.common.BaseViewModel
import com.mydigipay.challenge.common.help.SingleLiveEvent

/**
 * Reference:
 * [https://github.com/RohitSurwase/AAC-MVI-Architecture]
 */

open class MviViewModel<STATE, EFFECT, EVENT>(application: Application) :
    BaseViewModel(application), ViewModelContract<EVENT> {

    private val _viewStates: MutableLiveData<STATE> = MutableLiveData()
    fun viewStates(): LiveData<STATE> = _viewStates

    private var _viewState: STATE? = null
    protected var viewState: STATE
        get() = _viewState
            ?: throw UninitializedPropertyAccessException("\"viewState\" was queried before being initialized")
        set(value) {
            Log.d(TAG, "setting viewState : $value")
            _viewState = value
            _viewStates.value = value
        }


    private val _viewEffects: SingleLiveEvent<EFFECT> = SingleLiveEvent()
    fun viewEffects(): SingleLiveEvent<EFFECT> = _viewEffects

    private var _viewEffect: EFFECT? = null
    protected var viewEffect: EFFECT
        get() = _viewEffect
            ?: throw UninitializedPropertyAccessException("\"viewEffect\" was queried before being initialized")
        set(value) {
            Log.d(TAG, "setting viewEffect : $value")
            _viewEffect = value
            _viewEffects.value = value
        }

    @CallSuper
    override fun process(viewEvent: EVENT) {
        if (!viewStates().hasObservers()) {
            throw NoObserverAttachedException(
                "No observer attached. In case of custom View \"startObserving()\" function needs to be called manually."
            )
        }

        // DEBUG
        Log.d(TAG, "processing viewEvent: $viewEvent")
    }

    override fun onCleared() {
        super.onCleared()

        // DEBUG
        Log.d(TAG, "onCleared")
    }
}

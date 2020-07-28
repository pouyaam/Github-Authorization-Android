package com.mydigipay.challenge.presentation.design

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.mydigipay.challenge.common.BaseActivity

/**
 * Reference:
 * [https://github.com/RohitSurwase/AAC-MVI-Architecture]
 */

abstract class MviActivity<STATE, EFFECT, EVENT, ViewModel : MviViewModel<STATE, EFFECT, EVENT>> :
    BaseActivity() {

    lateinit var viewModel: ViewModel

    protected val viewStateObserver = Observer<STATE> {
        // DEBUG
        Log.d(TAG, "observed viewState : $it")

        renderViewState(it)
    }

    protected val viewEffectObserver = Observer<EFFECT> {
        // DEBUG
        Log.d(TAG, "observed viewEffect : $it")

        renderViewEffect(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Consider to start observing viewStates and viewEffects here,
         * using viewStateObserver and viewEffectObserver.
         */
    }

    abstract fun renderViewState(viewState: STATE)

    abstract fun renderViewEffect(viewEffect: EFFECT)
}

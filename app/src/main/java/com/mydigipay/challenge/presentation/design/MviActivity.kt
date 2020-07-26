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

    abstract val viewModel: ViewModel

    private val viewStateObserver = Observer<STATE> {
        // DEBUG
        Log.d(TAG, "observed viewState : $it")

        renderViewState(it)
    }

    private val viewEffectObserver = Observer<EFFECT> {
        // DEBUG
        Log.d(TAG, "observed viewEffect : $it")

        renderViewEffect(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.viewStates().observe(this, viewStateObserver)
        viewModel.viewEffects().observe(this, viewEffectObserver)
    }

    abstract fun renderViewState(viewState: STATE)

    abstract fun renderViewEffect(viewEffect: EFFECT)
}

/*
 * Copyright 2020 Rohit Surwase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mydigipay.challenge.presentation.design

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

/**
 * Reference:
 * [https://github.com/RohitSurwase/AAC-MVI-Architecture]
 */

abstract class MviCustomView<STATE, EFFECT, EVENT, ViewModel : MviViewModel<STATE, EFFECT, EVENT>> {

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

    /**
     * Consider to start observing viewStates and viewEffects here,
     * using viewStateObserver and viewEffectObserver.
     */
    fun startObserving(lifecycleOwner: LifecycleOwner) {
        viewModel.viewStates().observe(lifecycleOwner, viewStateObserver)
        viewModel.viewEffects().observe(lifecycleOwner, viewEffectObserver)
    }

    abstract fun renderViewState(viewState: STATE)

    abstract fun renderViewEffect(viewEffect: EFFECT)
}

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

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.mydigipay.challenge.common.BaseFragment

/**
 * Reference:
 * [https://github.com/RohitSurwase/AAC-MVI-Architecture]
 */

abstract class MviFragment<STATE, EFFECT, EVENT, ViewModel : MviViewModel<STATE, EFFECT, EVENT>> :
    BaseFragment() {

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

    /**
     * Consider to start observing viewStates and viewEffects here,
     * using viewStateObserver and viewEffectObserver.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, parent, savedInstanceState)
    }

    abstract fun renderViewState(viewState: STATE)

    abstract fun renderViewEffect(viewEffect: EFFECT)
}

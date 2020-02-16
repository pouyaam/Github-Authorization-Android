package com.mydigipay.challenge.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mydigipay.challenge.utils.Coroutines
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@UseExperimental(FlowPreview::class)
class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {


    private val viewState = ProfileViewState()

    val profileState = MutableLiveData<ProfileViewState>()

    init {
        Coroutines.ioThenMain({ profileRepository.getProfile() }) {
            onExecute { profileState.value = viewState.copy(isLoading = true) }
            finally { profileState.value = viewState.copy(isLoading = false) }
            onComplete {
                profileState.value = viewState.copy(
                    isLoading = false,
                    user = it,
                    error = null
                )
            }
            onError { profileState.value = viewState.copy(isLoading = false, error = it) }
        }
    }

}
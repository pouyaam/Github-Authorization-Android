package com.mydigipay.challenge.base

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.mydigipay.challenge.util.ActionCommand
import com.mydigipay.challenge.util.NavigationCommand
import com.mydigipay.challenge.util.livedata.Event

abstract class BaseViewModel() : ViewModel() {

    /** Used in situations when we need to navigate to another fragment/activity
     */
    private val _navigationCommand = MutableLiveData<Event<NavigationCommand>>()
    val navigationCommand: LiveData<Event<NavigationCommand>>
        get() = _navigationCommand


    /**
     *      getting directions from the navigation's built actions
     */
    fun navigateTo(directions: NavDirections) {
        _navigationCommand.postValue(Event(NavigationCommand.To(directions)))
    }

    /**
     * using [NavController][androidx.navigation.NavController]'s navigateUp()
     */
    fun navigateBack() {
        _navigationCommand.postValue(Event(NavigationCommand.Back))
    }

    /**
     * using [NavController][androidx.navigation.NavController]'s popBackStack()
     */
    fun navigateBackTo(destinationId: Int, inclusive: Boolean) {
        _navigationCommand.postValue(Event(NavigationCommand.BackTo(destinationId, inclusive)))
    }

    private val _actionCommand = MutableLiveData<Event<ActionCommand>>()
    val actionCommand: LiveData<Event<ActionCommand>>
        get() = _actionCommand

    fun openIntent(action: String? = null, type: String? = null, uri: Uri? = null) {
        _actionCommand.postValue(Event(ActionCommand.OpenUrl(action, type, uri)))
    }

}
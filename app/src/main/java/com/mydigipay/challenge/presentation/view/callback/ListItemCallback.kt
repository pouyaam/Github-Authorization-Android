package com.mydigipay.challenge.presentation.view.callback

import android.view.View
import com.mydigipay.challenge.presentation.model.ListItem

interface ListItemCallback {

    fun onClick(item: ListItem.GithubRepoListItem) {
        /* Default interface function */
    }

    fun onLongClick(view: View?, item: ListItem.GithubRepoListItem): Boolean {
        /* Default interface function */
        return true
    }

    fun onClick(item: ListItem.LoadingListItem) {
        /* Default interface function */
    }

    fun onLongClick(view: View?, item: ListItem.LoadingListItem): Boolean {
        /* Default interface function */
        return true
    }

    fun onClick(item: ListItem.ErrorListItem) {
        /* Default interface function */
    }

    fun onLongClick(view: View?, item: ListItem.ErrorListItem): Boolean {
        /* Default interface function */
        return true
    }
}

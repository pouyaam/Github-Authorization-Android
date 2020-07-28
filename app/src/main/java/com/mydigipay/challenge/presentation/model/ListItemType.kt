package com.mydigipay.challenge.presentation.model

enum class ListItemType(val value: Int) {

    LOADING_ITEM(0), ERROR_ITEM(2), GITHUB_REPO_ITEM(3);

    companion object {
        fun ofValue(value: Int): ListItemType? {
            for (userIssue in values()) {
                if (userIssue.value == value) {
                    return userIssue
                }
            }

            return null
        }
    }
}

package com.mydigipay.challenge.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.mydigipay.challenge.presentation.model.ListItem
import com.mydigipay.challenge.presentation.model.ListItemType

class ListItemDistinguisher(
    private val oldSet: MutableList<ListItem>,
    private val newSet: MutableList<ListItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldSet.size
    }

    override fun getNewListSize(): Int {
        return newSet.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldSet[oldItemPosition]
        val newItem = newSet[newItemPosition]

        return if (oldItem.viewType == ListItemType.GITHUB_REPO_ITEM &&
                   newItem.viewType == ListItemType.GITHUB_REPO_ITEM
        ) {
            (newItem as ListItem.GithubRepoListItem).githubRepo.id ==
                    (oldItem as ListItem.GithubRepoListItem).githubRepo.id
        }
        else {
            false
        }
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem = oldSet[oldItemPosition]
        val newItem = newSet[newItemPosition]

        return if (oldItem.viewType == ListItemType.GITHUB_REPO_ITEM &&
                   newItem.viewType == ListItemType.GITHUB_REPO_ITEM
        ) {
            return (newItem as ListItem.GithubRepoListItem).githubRepo ==
                    (oldItem as ListItem.GithubRepoListItem).githubRepo
        }
        else {
            false
        }
    }
}

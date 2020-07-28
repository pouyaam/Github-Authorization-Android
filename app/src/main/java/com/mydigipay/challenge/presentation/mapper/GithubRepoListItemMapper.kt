package com.mydigipay.challenge.presentation.mapper

import com.mydigipay.challenge.mapper.DataMapper
import com.mydigipay.challenge.presentation.model.ListItem
import com.mydigipay.challenge.repositories.GithubRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepoListItemMapper @Inject constructor() :
    DataMapper<GithubRepo, ListItem.GithubRepoListItem>() {

    override fun transformToEntity(model: GithubRepo): ListItem.GithubRepoListItem? {
        return ListItem.GithubRepoListItem(model)
    }

    override fun transformToModel(entity: ListItem.GithubRepoListItem): GithubRepo? {
        // Unnecessary transform
        return null
    }
}

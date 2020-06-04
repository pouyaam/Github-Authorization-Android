package com.mydigipay.challenge.mvvm.repositorysearchfragment.adapter

import androidx.databinding.ObservableField
import com.mydigipay.challenge.infrastructure.data.model.api.repositorysearch.ItemRepository

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 * mahdiZTD@gmail.com
 */
class RepositoryItemViewModel(
    private val itemRepository: ItemRepository,
    private val clickListener: RepositoryAdapter.OnItemClickListener?
) {

    val author = ObservableField<String>(itemRepository.owner?.login)
    val authorAvatar = ObservableField<String>(itemRepository.owner?.avatarUrl)
    val title = ObservableField<String>(itemRepository.fullName)
    val description = ObservableField<String>(itemRepository.description)
    val publishedAt = ObservableField<String>(itemRepository.createdAt)

    fun onCommitClick() {
        clickListener?.onRepoCommitsClicked(itemRepository.owner?.login,itemRepository.name)
    }

    fun onProfileClicked(){
        clickListener?.onRepoUserProfileClicked(itemRepository.owner?.login)
    }
}
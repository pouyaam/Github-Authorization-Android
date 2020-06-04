package com.mydigipay.challenge.mvvm.repocommitsfragment.adapter

import androidx.databinding.ObservableField
import com.mydigipay.challenge.infrastructure.data.model.api.repositorycommit.ResponseRepositoryCommits

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/4/2020.
 *
 * mahdiZTD@gmail.com
 */
class CommitItemViewModel(
    itemRepository: ResponseRepositoryCommits
) {

    val author = ObservableField<String>(itemRepository.commit?.author?.name)
    val title = ObservableField<String>(itemRepository.commit?.message)
    val description = ObservableField<String>(itemRepository.commit?.author?.email)
    val publishedAt = ObservableField<String>(itemRepository.commit?.author?.date)

}
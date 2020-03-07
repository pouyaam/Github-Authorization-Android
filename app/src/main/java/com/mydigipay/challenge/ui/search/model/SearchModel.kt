package com.mydigipay.challenge.ui.search.model

import com.mydigipay.challenge.dataaccess.ProjectDataSource

class SearchModel(
    private val repository: ProjectDataSource
) {
    fun search(word: String, page: Int) = repository.search(word, page)
}
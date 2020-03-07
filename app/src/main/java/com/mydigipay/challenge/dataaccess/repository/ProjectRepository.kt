package com.mydigipay.challenge.dataaccess.repository

import com.mydigipay.challenge.dataaccess.ProjectDataSource

class ProjectRepository(
    private val remote: ProjectDataSource
) : ProjectDataSource {
    override fun search(word: String, page: Int) = remote.search(word, page)
}
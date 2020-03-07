package com.mydigipay.challenge.dataaccess.remote

import com.mydigipay.challenge.dataaccess.ProjectDataSource

class ProjectRemoteDataSource(
    private val api: GitApiService
) : ProjectDataSource {
    override fun search(word: String, page: Int) = api.getProjects(word, page)
}
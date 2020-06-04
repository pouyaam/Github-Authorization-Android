package com.mydigipay.challenge.infrastructure.data

import com.mydigipay.challenge.infrastructure.data.local.preference.PreferenceHelper
import com.mydigipay.challenge.infrastructure.data.model.api.RequestAccessToken
import com.mydigipay.challenge.infrastructure.data.model.api.ResponseAccessToken
import com.mydigipay.challenge.infrastructure.data.model.api.repositorycommit.ResponseRepositoryCommits
import com.mydigipay.challenge.infrastructure.data.model.api.repositorysearch.ResponseRepositorySearch
import com.mydigipay.challenge.infrastructure.data.remote.ApiHelper
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/3/2020.
 *
 * mahdiZTD@gmail.com
 */

interface DataManager : ApiHelper, PreferenceHelper {
}

class DataManagerImp @Inject constructor(
    private val apiHelper: ApiHelper,
    private val preferenceHelper: PreferenceHelper
) : DataManager {
    override fun getToken(): String? =
        preferenceHelper.getToken()

    override fun setToken(accessToken: String?) =
        preferenceHelper.setToken(accessToken)


    override fun accessToken(requestAccessToken: RequestAccessToken): Single<ResponseAccessToken> =
        apiHelper.accessToken(requestAccessToken)

    override fun searchRepositories(repoSearch: String): Single<ResponseRepositorySearch> =
        apiHelper.searchRepositories(repoSearch)

    override fun getRepositoryCommits(login:String,name:String): Single<MutableList<ResponseRepositoryCommits>> =
        apiHelper.getRepositoryCommits(login,name)

}
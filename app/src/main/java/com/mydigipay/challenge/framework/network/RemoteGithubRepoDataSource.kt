package com.mydigipay.challenge.framework.network

import android.util.Log
import com.mydigipay.challenge.framework.network.mapper.GithubRepoMapper
import com.mydigipay.challenge.model.Resource
import com.mydigipay.challenge.repositories.GithubRepo
import com.mydigipay.challenge.repositories.GithubRepoDataSource
import java.io.IOException
import javax.inject.Inject

class RemoteGithubRepoDataSource @Inject constructor(
    private val service: RemoteGithubRepoService,
    private val mapper: GithubRepoMapper
) : GithubRepoDataSource {

    override suspend fun getGithubRepos(since: Int, token: String): Resource<List<GithubRepo>> {
        return try {
            val response = service.fetchGithubRepos(since, token)

            return if (response.isSuccessful) {
                Resource.success(mapper.transformToModels(response.body()!!))
            }
            else {
                /* Handle standard error codes, by checking [response.code()] */

                Resource.error(
                    IOException(response.errorBody()?.string() ?: "Something goes wrong")
                )
            }
        }
        catch (e: Exception) {
            // DEBUG
            Log.e("getGithubRepos", e.message ?: "Internet error runs")

            Resource.error(IOException(e.message ?: "Internet error runs"))
        }
    }
}

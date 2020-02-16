package com.mydigipay.challenge.ui.home

import android.content.SharedPreferences
import com.mydigipay.challenge.base.FinishException
import com.mydigipay.challenge.network.oauth.GithubApiService
import com.mydigipay.challenge.network.oauth.RequestAccessToken
import com.mydigipay.challenge.utils.getToken
import com.mydigipay.challenge.utils.saveAuthorization
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.await

private const val CODE = "CODE"

class HomeRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val githubApiService: GithubApiService
) : HomeRepository {
    override var code: String? = null
        get() = sharedPreferences.getString(CODE, null)
        set(value) {
            value ?: return
            if (value == field)
                return
            sharedPreferences.edit().putString(CODE, value).apply()
        }

    override suspend fun saveToken(token: String) =
        withContext(Dispatchers.IO) {
            saveAuthorization(token)
        }


    override suspend fun getRepositories(code: String): RepositoryResult =
        try {
            this.code = code
            RepositoryResult.Success(githubApiService.getRepositories().await())
        } catch (t: Throwable) {
            if (t is HttpException)
                when (t.code()) {
                    401 -> {
                        if (getToken(code))
                            getRepositories(code)
                        else
                            throw t

                    }
                    else -> throw t
                }
            else
                throw t
        }


    override suspend fun getToken(code: String) =
        code.takeIf { it.isNotEmpty() }?.let {
            withContext(Dispatchers.IO) {
                getToken() ?: githubApiService.accessToken(
                    RequestAccessToken(
                        HomeFragment.CLIENT_ID,
                        HomeFragment.CLIENT_SECRET,
                        code,
                        HomeFragment.REDIRECT_URI,
                        "0"
                    )
                ).await().accessToken.also { saveToken(it) }
            }.isNotBlank()
        } ?: throw FinishException("Code is null or empty")

}
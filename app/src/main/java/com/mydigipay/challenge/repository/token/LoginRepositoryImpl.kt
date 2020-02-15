package com.mydigipay.challenge.repository.token

import android.content.SharedPreferences
import com.mydigipay.challenge.base.FinishException
import com.mydigipay.challenge.network.oauth.GithubApiService
import com.mydigipay.challenge.network.oauth.RequestAccessToken
import com.mydigipay.challenge.repository.getToken
import com.mydigipay.challenge.repository.saveAuthorization
import com.mydigipay.challenge.ui.home.HomeFragment
import com.mydigipay.challenge.ui.home.LoginResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.await

private const val CODE = "CODE"

class LoginRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val githubApiService: GithubApiService
) : LoginRepository {
    override var code: String?
        get() = sharedPreferences.getString(CODE, null)
        set(value) {
            value ?: return
            sharedPreferences.edit().putString(CODE, value).apply()
        }

    override suspend fun saveToken(token: String) =
        CoroutineScope(Dispatchers.IO).async {
            saveAuthorization(token)
        }.await()


    override suspend fun getRepositories(code: String): LoginResult =
        try {
            LoginResult.Success(githubApiService.getRepositories().await())
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
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
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
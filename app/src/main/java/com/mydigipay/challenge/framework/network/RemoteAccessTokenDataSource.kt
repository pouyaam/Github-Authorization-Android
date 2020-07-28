package com.mydigipay.challenge.framework.network

import android.util.Log
import com.mydigipay.challenge.authorization.AccessToken
import com.mydigipay.challenge.authorization.AccessTokenDataSource
import com.mydigipay.challenge.framework.network.mapper.AccessTokenMapper
import com.mydigipay.challenge.framework.network.request.AccessTokenRequest
import com.mydigipay.challenge.model.Resource
import java.io.IOException
import javax.inject.Inject

class RemoteAccessTokenDataSource @Inject constructor(
    private val service: RemoteAccessTokenService,
    private val mapper: AccessTokenMapper
) : AccessTokenDataSource {

    override suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String,
        state: String
    ): Resource<AccessToken> {
        return try {
            val response = service.fetchAccessToken(
                AccessTokenRequest(clientId, clientSecret, code, redirectUri, state)
            )

            return if (response.isSuccessful) {
                Resource.success(mapper.transformToModel(response.body()!!)!!)
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
            Log.e("getAccessToken", e.message ?: "Internet error runs")

            Resource.error(IOException(e.message ?: "Internet error runs"))
        }
    }
}

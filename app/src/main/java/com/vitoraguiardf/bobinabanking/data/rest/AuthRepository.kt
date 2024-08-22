package com.vitoraguiardf.bobinabanking.data.rest

import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.data.entity.JsonWebToken
import com.vitoraguiardf.bobinabanking.data.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class AuthRepository {
    private val retrofitInterface: Class<AuthInterface> = AuthInterface::class.java
    suspend fun login(username: String, password: String): Result<JsonWebToken> {
        val endPoint = Singleton.instance.retrofit.create(retrofitInterface)
        return withContext(Dispatchers.IO) {
            try {
                val response = endPoint.login(username, password).execute()
                if (response.errorBody() != null) {
                    Result.failure(Throwable("${response.code()}: ${response.errorBody()!!.string()}"))
                } else {
                    val responseBody = response.body()
                    System.out.println(responseBody)
                    var token: JsonWebToken? = null
                    Result.success(responseBody!!)
                }
            } catch (ex: IOException) {
                Result.failure(ex)
            }
        }
    }
    suspend fun me(): Result<User> {
        val endPoint = Singleton.instance.retrofit.create(retrofitInterface)
        return withContext(Dispatchers.IO) {
            try {
                val response = endPoint.me().execute()
                if (response.errorBody() != null) {
                    Result.failure(Throwable(response.errorBody()!!.string()))
                } else {
                    val responseBody = response.body()
                    var user: User? = null
                    Result.success(responseBody!!)
                }
            } catch (ex: IOException) {
                Result.failure(ex)
            }
        }
    }
}
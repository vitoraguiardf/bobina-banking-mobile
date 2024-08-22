package com.vitoraguiardf.bobinabanking.data.rest

import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.data.entity.JsonWebToken
import com.vitoraguiardf.bobinabanking.data.entity.Message
import com.vitoraguiardf.bobinabanking.data.entity.User

class AuthRepository: AbstractRestRepository() {

    private val retrofit = Singleton.instance.retrofit.create(AuthInterface::class.java)

    suspend fun login(username: String, password: String): Result<JsonWebToken> {
        return execute(retrofit.login(username, password))
    }

    suspend fun refresh(): Result<JsonWebToken> {
        return execute(retrofit.refresh())
    }

    suspend fun me(): Result<User> {
        return execute(retrofit.me())
    }

    suspend fun logout(): Result<Message> {
        return execute(retrofit.logout())
    }

}
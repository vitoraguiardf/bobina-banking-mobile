package com.vitoraguiardf.bobinabanking.data.rest

import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.data.entity.Account

class AccountRepository: AbstractRestRepository() {

    private val retrofit = Singleton.instance.retrofit.create(AccountInterface::class.java)

    suspend fun get(): Result<Array<Account>> {
        return execute(retrofit.get())
    }

    suspend fun search(email: String): Result<Array<Account>> {
        return execute(retrofit.search(email))
    }

}
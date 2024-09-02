package com.vitoraguiardf.bobinabanking.data.rest

import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.data.entity.CoilTransaction
import com.vitoraguiardf.bobinabanking.data.entity.User

class TransactionRepository: AbstractRestRepository() {

    private val retrofit = Singleton.instance.retrofit.create(TransactionInterface::class.java)

    suspend fun resume(): Result<User> {
        return execute(retrofit.resume())
    }

    suspend fun transactions(): Result<Array<CoilTransaction>> {
        return execute(retrofit.transactions())
    }

}
package com.vitoraguiardf.bobinabanking.data.rest

import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.data.entity.Transaction
import com.vitoraguiardf.bobinabanking.data.entity.User

class TransactionRepository: AbstractRestRepository() {

    private val retrofit = Singleton.instance.retrofit.create(TransactionInterface::class.java)

    suspend fun resume(): Result<User> {
        return execute(retrofit.resume())
    }

    suspend fun transactions(): Result<Array<Transaction>> {
        return execute(retrofit.transactions())
    }

}
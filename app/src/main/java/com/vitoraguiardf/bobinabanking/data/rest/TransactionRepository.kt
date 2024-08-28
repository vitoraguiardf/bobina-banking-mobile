package com.vitoraguiardf.bobinabanking.data.rest

import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.data.entity.Transaction

class TransactionRepository: AbstractRestRepository() {

    private val retrofit = Singleton.instance.retrofit.create(TransactionInterface::class.java)

    suspend fun transactions(): Result<Array<Transaction>> {
        return execute(retrofit.transactions())
    }

}
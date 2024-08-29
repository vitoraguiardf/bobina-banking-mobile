package com.vitoraguiardf.bobinabanking.data.rest

import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.data.entity.TransactionType

class TransactionTypeRepository: AbstractRestRepository() {

    private val retrofit = Singleton.instance.retrofit.create(TransactionTypeInterface::class.java)

    suspend fun get(): Result<Array<TransactionType>> {
        return execute(retrofit.get())
    }

}
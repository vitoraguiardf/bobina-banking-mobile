package com.vitoraguiardf.bobinabanking.data.rest

import com.vitoraguiardf.bobinabanking.data.entity.TransactionType
import retrofit2.Call
import retrofit2.http.GET

interface TransactionTypeInterface {

    @GET("bobina-banking/transaction-type")
    fun get(): Call<Array<TransactionType>>

}
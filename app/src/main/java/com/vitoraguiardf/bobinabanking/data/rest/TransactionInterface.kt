package com.vitoraguiardf.bobinabanking.data.rest

import com.vitoraguiardf.bobinabanking.data.entity.Transaction
import retrofit2.Call
import retrofit2.http.GET

interface TransactionInterface {

    @GET("bobina-banking/transactions")
    fun transactions(): Call<Array<Transaction>>

}
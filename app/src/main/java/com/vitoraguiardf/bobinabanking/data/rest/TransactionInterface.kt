package com.vitoraguiardf.bobinabanking.data.rest

import com.vitoraguiardf.bobinabanking.data.entity.CoilTransaction
import com.vitoraguiardf.bobinabanking.data.entity.User
import retrofit2.Call
import retrofit2.http.GET

interface TransactionInterface {

    @GET("bobina-banking/resume")
    fun resume(): Call<User>

    @GET("bobina-banking/transactions")
    fun transactions(): Call<Array<CoilTransaction>>

}
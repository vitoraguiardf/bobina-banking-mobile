package com.vitoraguiardf.bobinabanking.data.rest

import com.vitoraguiardf.bobinabanking.data.entity.CoilTransaction
import com.vitoraguiardf.bobinabanking.data.entity.Message
import com.vitoraguiardf.bobinabanking.data.entity.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TransactionInterface {

    @GET("bobina-banking/resume")
    fun resume(): Call<User>

    @GET("bobina-banking/transactions")
    fun transactions(): Call<Array<CoilTransaction>>

    @POST("bobina-banking/transactions")
    fun post(@Body transaction: Transaction): Call<Message>

}
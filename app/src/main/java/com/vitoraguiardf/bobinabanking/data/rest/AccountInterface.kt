package com.vitoraguiardf.bobinabanking.data.rest

import com.vitoraguiardf.bobinabanking.data.entity.Account
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AccountInterface {

    @GET("bobina-banking/accounts")
    fun get(): Call<Array<Account>>

    @POST("bobina-banking/accounts/search")
    fun search(@Query("email") email: String): Call<Array<Account>>

}
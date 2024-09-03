package com.vitoraguiardf.bobinabanking.data.rest

import com.vitoraguiardf.bobinabanking.data.entity.Account
import retrofit2.Call
import retrofit2.http.GET

interface AccountInterface {

    @GET("bobina-banking/accounts")
    fun get(): Call<Array<Account>>

}
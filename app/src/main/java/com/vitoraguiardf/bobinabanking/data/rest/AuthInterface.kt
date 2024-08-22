package com.vitoraguiardf.bobinabanking.data.rest

import com.vitoraguiardf.bobinabanking.data.entity.JsonWebToken
import com.vitoraguiardf.bobinabanking.data.entity.Message
import com.vitoraguiardf.bobinabanking.data.entity.User

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthInterface {

    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("email") username: String,
        @Field("password") password: String
    ): Call<JsonWebToken>

    @GET("auth/me")
    fun me(): Call<User>

    @GET("auth/refresh")
    fun refresh(): Call<JsonWebToken>

    @GET("auth/logout")
    fun logout(): Call<Message>

}
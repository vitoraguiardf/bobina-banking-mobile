package com.vitoraguiardf.bobinabanking.data.rest

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestClient(val context: Context) {
    private val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
    private val client = OkHttpClient.Builder()
        .connectionSpecs(listOf(
            ConnectionSpec.MODERN_TLS,
            ConnectionSpec.COMPATIBLE_TLS,
            ConnectionSpec.CLEARTEXT
        ))
        .addNetworkInterceptor(interceptor())
        .build()
    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl("http://192.168.0.214:8000/") // .baseUrl(context.getString(R.string.host_name))
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
    val retrofit: Retrofit = retrofitBuilder.build()

    private fun interceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
            val rBuilder = request.newBuilder()
            if (!request.url().toString().endsWith("login")) {
                TODO("Add Token to Client")
//              getCurrentToken?.let {
//                  rBuilder.addHeader("Authorization", token.getAuthorization())
//                  rBuilder.addHeader("Accept", "application/json")
//              }
            }
            val response = chain.proceed(rBuilder.build())
            val responseCode = response.code()
            val responseMessage = response.message()
            val requestedUrl = request.url().toString()
            if (HttpStatus.UNAUTHORIZED.equals(responseCode)) {
                if (responseMessage.startsWith("Unauthenticated")) {
                    if (requestedUrl.endsWith("auth/refresh")) {
                        TODO("Auth refresh fails")
                    } else {
                        TODO("Attempt Refresh Token")
                    }
                }
            } else if (HttpStatus.INTERNAL_SERVER_ERROR.equals(responseCode)) {
                if (responseMessage.startsWith("Token Signature could not be verified.")
                    || responseMessage.startsWith("Token has expired and can no longer be refreshed")) {
                    if (!requestedUrl.endsWith("auth/logout")) {
                        // TODO("Auth refresh fails")
                    }
                }
            }
            response
        }
    }
}
package com.vitoraguiardf.bobinabanking.data.rest

import android.content.Context
import com.google.gson.GsonBuilder
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.utils.enums.HttpStatus
import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestClientConfig(context: Context) {
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
        .baseUrl(context.getString(R.string.server_api))
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
    val retrofit: Retrofit = retrofitBuilder.build()

    private fun interceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
            val rBuilder = request.newBuilder()
            if (!request.url().toString().endsWith("login")) {
                rBuilder.addHeader("Accept", "application/json")
                Singleton.instance.token?.let {
                    rBuilder.addHeader("Authorization", it.getAuthorization())
                }
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
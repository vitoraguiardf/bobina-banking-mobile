package com.vitoraguiardf.bobinabanking

import android.app.Application
import com.vitoraguiardf.bobinabanking.data.rest.RestClient
import retrofit2.Retrofit

class Singleton: Application() {
    val retrofit: Retrofit = RestClient(this).retrofit

    override fun onCreate() {
        super.onCreate()
        _instance = _instance ?: this
    }
    companion object {
        private var _instance: Singleton? = null
        val instance get() = _instance!!
    }
}
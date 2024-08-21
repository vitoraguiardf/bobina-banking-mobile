package com.vitoraguiardf.bobinabanking

import android.app.Application
import android.os.Looper
import androidx.core.os.HandlerCompat
import com.vitoraguiardf.bobinabanking.data.rest.RestClient
import retrofit2.Retrofit
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Singleton: Application() {
    val executor: ExecutorService = Executors.newFixedThreadPool(4)
    val handler = HandlerCompat.createAsync(Looper.getMainLooper())
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
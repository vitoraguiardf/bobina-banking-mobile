package com.vitoraguiardf.bobinabanking

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.core.os.HandlerCompat
import com.vitoraguiardf.bobinabanking.data.rest.RestClientConfig
import retrofit2.Retrofit
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Singleton: Application() {
    lateinit var executor: ExecutorService
    lateinit var handler: Handler
    lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()
        lazyInit(this)
    }

    private fun lazyInit(singleton: Singleton) {
        if (_instance == null) {
            _instance = singleton
            retrofit = RestClientConfig(singleton).retrofit
            executor = Executors.newFixedThreadPool(4)
            handler = HandlerCompat.createAsync(Looper.getMainLooper())
        }
    }

    companion object {
        private var _instance: Singleton? = null
        val instance get() = _instance!!
    }
}
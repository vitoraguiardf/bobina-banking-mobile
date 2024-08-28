package com.vitoraguiardf.bobinabanking

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.core.os.HandlerCompat
import com.vitoraguiardf.bobinabanking.data.AppDatabase
import com.vitoraguiardf.bobinabanking.data.entity.JsonWebToken
import com.vitoraguiardf.bobinabanking.data.entity.User
import com.vitoraguiardf.bobinabanking.data.rest.RestClientConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Singleton: Application() {
    lateinit var executor: ExecutorService
    lateinit var handler: Handler
    lateinit var retrofit: Retrofit
    var token: JsonWebToken? = null
    lateinit var user: User

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

    suspend fun runDatabase(runOnDatabase: (database: AppDatabase) -> Unit) {
        withContext(Dispatchers.IO) {
            synchronized(Dispatchers.IO) {
                runOnDatabase(AppDatabase(baseContext))
            }
        }
    }

    companion object {
        private var _instance: Singleton? = null
        val instance get() = _instance!!
    }

}
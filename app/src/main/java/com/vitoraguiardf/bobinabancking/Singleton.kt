package com.vitoraguiardf.bobinabancking

import android.app.Application

class Singleton: Application() {
    override fun onCreate() {
        super.onCreate()
        _instance = _instance ?: this
    }
    companion object {
        private var _instance: Singleton? = null
        val instance get() = _instance!!
    }
}
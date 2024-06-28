package com.vitoraguiardf.bobinabancking

import androidx.appcompat.app.AppCompatActivity

/**
 * Wrapper for AppCompatActivity adding helpers to activity
 */
abstract class CustomActivity: AppCompatActivity() {
    companion object {
        // Helper to access single app instance
        internal val singleton
            get()= Singleton.instance
    }
}
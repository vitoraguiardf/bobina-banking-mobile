package com.vitoraguiardf.bobinabanking

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vitoraguiardf.bobinabanking.databinding.ActivityMainBinding

class MainActivity : CustomActivity() {
    private lateinit var view: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)
        ViewCompat.setOnApplyWindowInsetsListener(view.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Show singleton instance on start app
        Toast.makeText(this, singleton.toString(), Toast.LENGTH_SHORT).show()
    }
}
package com.vitoraguiardf.bobinabanking.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.vitoraguiardf.bobinabanking.utils.activities.CustomActivity
import com.vitoraguiardf.bobinabanking.databinding.ActivityMainBinding
import com.vitoraguiardf.bobinabanking.ui.login.LoginActivity

class MainActivity : CustomActivity<ActivityMainBinding>() {
    private lateinit var viewModel: MainViewModel
    override fun viewBindingInflate(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.actionSignIn.setOnClickListener {
            launcher.launch(Intent(this, LoginActivity::class.java))
        }
        binding.actionSignUp.visibility = View.GONE
    }
}
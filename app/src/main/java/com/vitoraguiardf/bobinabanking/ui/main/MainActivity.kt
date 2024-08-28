package com.vitoraguiardf.bobinabanking.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vitoraguiardf.bobinabanking.databinding.ActivityMainBinding
import com.vitoraguiardf.bobinabanking.ui.home.HomeActivity
import com.vitoraguiardf.bobinabanking.ui.login.LoginActivity
import com.vitoraguiardf.bobinabanking.utils.activities.CustomActivity
import com.vitoraguiardf.bobinabanking.utils.viewmodel.FormState

class MainActivity : CustomActivity<ActivityMainBinding>() {
    private lateinit var viewModel: MainViewModel

    override fun viewBindingInflate(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.form.state.observe(this@MainActivity, Observer {
            val state = it?: return@Observer

            binding.actionSignInContinue.visibility = when(state) {
                FormState.SUCCESS -> View.VISIBLE
                else -> View.GONE
            }

            binding.actionSignIn.visibility = when(state) {
                FormState.FAILED -> View.VISIBLE
                else -> View.GONE
            }

            binding.actionSignUp.visibility = when(state) {
                FormState.FAILED -> View.VISIBLE
                else -> View.GONE
            }

            binding.included.loading.visibility = when(state) {
                FormState.RUNNING -> View.VISIBLE
                else -> View.GONE
            }

        })
        viewModel.form.result.observe(this@MainActivity, Observer {
            val user = it?: return@Observer
            binding.actionSignInContinue.text = "Continuar como ${user.name}!"
        })
        viewModel.form.throwable.observe(this@MainActivity, Observer {
            val throwable = it?: return@Observer
            Toast.makeText(this@MainActivity, throwable.message, Toast.LENGTH_LONG).show()
        })
        binding.actionSignIn.setOnClickListener {
            launcher.launch(Intent(this, LoginActivity::class.java))
        }
        binding.actionSignUp.setOnClickListener {}
        binding.actionSignInContinue.setOnClickListener {
             launcher.launch(Intent(this, HomeActivity::class.java))
        }
        binding.actionSignUp.visibility = View.GONE
        binding.actionSignInContinue.visibility = View.GONE
        verifySession()
    }

    override fun onPostResume() {
        super.onPostResume()
        verifySession()
    }

    private fun verifySession() {
        if (instance.token != null) {
            viewModel.me()
        }
    }

}
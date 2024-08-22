package com.vitoraguiardf.bobinabanking.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.utils.activities.CustomActivity
import com.vitoraguiardf.bobinabanking.databinding.ActivityMainBinding
import com.vitoraguiardf.bobinabanking.ui.home.HomeActivity
import com.vitoraguiardf.bobinabanking.ui.login.LoginActivity
import com.vitoraguiardf.bobinabanking.utils.viewmodel.FormState

class MainActivity : CustomActivity<ActivityMainBinding>() {
    private lateinit var viewModel: MainViewModel

    override fun viewBindingInflate(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.form.state.observe(this@MainActivity, Observer {
            val state = it?: return@Observer
            when(state) {
                FormState.SUCCESS -> {
                    setLoadingState(false)
                    return@Observer
                }
                FormState.FAILED -> {
                    setLoadingState(false)
                    return@Observer
                }
                FormState.RUNNING -> {
                    setLoadingState(true)
                    return@Observer
                }
            }
        })
        viewModel.form.result.observe(this@MainActivity, Observer {
            val user = it?: return@Observer
            Toast.makeText(this@MainActivity, "Bem vindo ${user.name}!", Toast.LENGTH_LONG).show()
            binding.actionSignIn.visibility = View.GONE
            binding.actionSignUp.visibility = View.GONE
            binding.actionSignInContinue.text = "Continuar como ${user.name}!"
            binding.actionSignInContinue.visibility = View.VISIBLE
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

    }

    override fun onPostResume() {
        super.onPostResume()
        verifySession()
    }

    private fun verifySession() {
        if (instance.token != null) {
            viewModel.me()
        } else {
            setLoadingState(false)
        }
    }

    private fun setLoadingState(loading: Boolean) {
        binding.actionSignIn.isEnabled = !loading
        binding.actionSignIn.text = if (!loading) getString(R.string.action_sign_in) else getString(R.string.loading)
        binding.loading.visibility = if (!loading) View.GONE else View.VISIBLE
    }

}
package com.vitoraguiardf.bobinabanking.ui.login

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vitoraguiardf.bobinabanking.AbstractCustomActivity
import com.vitoraguiardf.bobinabanking.databinding.ActivityLoginBinding
import com.vitoraguiardf.bobinabanking.utils.viewmodel.FormState

class LoginActivity: AbstractCustomActivity<ActivityLoginBinding>() {
    private lateinit var loginViewModel: LoginViewModel

    override fun viewBindingInflate(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginViewModel.form.validationError.observe(this@LoginActivity, Observer {
            val validator = it?: return@Observer
            binding.login.isEnabled = validator.noHasError()
            binding.username.error = validator.usernameError?.let { error -> getString(error) }
            binding.password.error = validator.passwordError?.let { error -> getString(error) }
        })
        loginViewModel.form.state.observe(this@LoginActivity, Observer {
            val state = it?: return@Observer
            when(state) {
                FormState.SUCCESS -> {
                    setLoadingState(false)
                    setResult(Activity.RESULT_OK)
                    finish()
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
        loginViewModel.form.result.observe(this@LoginActivity, Observer {
            val result = it?: return@Observer
            Toast.makeText(this@LoginActivity, result, Toast.LENGTH_LONG).show()
        })

        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {} // ignore
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {} // ignore
            override fun afterTextChanged(s: Editable) = loginViewModel.loginDataChanged(binding.username, binding.password)
        }

        binding.username.addTextChangedListener(textWatcher)
        binding.username.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                if (binding.username.text.toString().endsWith("@")) {
                    binding.username.text.append("gmail.com")
                }
            }
            return@setOnEditorActionListener loginViewModel.usernameValidator(binding.username.text.toString()) != null
        }

        binding.password.addTextChangedListener(textWatcher)
        binding.password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                requestLogin()
            }
            return@setOnEditorActionListener loginViewModel.passwordValidator(binding.password.text.toString()) != null
        }

        binding.login.setOnClickListener { requestLogin() }

    }

    private fun setLoadingState(loading: Boolean) {
        binding.username.isEnabled = !loading
        binding.password.isEnabled = !loading
        binding.login.isEnabled = !loading
        binding.loading.visibility = if (!loading) View.GONE else View.VISIBLE
    }

    private fun requestLogin() {
        loginViewModel.login(binding.username, binding.password, binding.rememberMeCheckBox!!.isChecked)
    }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED)
        super.onBackPressed()
    }
}
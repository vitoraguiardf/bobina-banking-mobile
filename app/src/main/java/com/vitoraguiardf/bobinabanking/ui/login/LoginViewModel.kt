package com.vitoraguiardf.bobinabanking.ui.login

import android.util.Patterns
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.data.entity.JsonWebToken
import com.vitoraguiardf.bobinabanking.data.rest.AuthRepository
import com.vitoraguiardf.bobinabanking.utils.viewmodel.FormState
import com.vitoraguiardf.bobinabanking.utils.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel: ViewModel<Int, String, LoginViewModel.LoginFormErrors>() {

    fun login(username: EditText, password: EditText, remember: Boolean) {
        this@LoginViewModel.login(username.text.toString(), password.text.toString(), remember)
    }
    fun login(username: String, password: String, remember: Boolean) {
        this@LoginViewModel.form.internalState.value = FormState.RUNNING
        viewModelScope.launch {
            start()
            withContext(Dispatchers.IO) {
                val resultLogin: Result<JsonWebToken> = repository.login(username, password)
                if (resultLogin.isSuccess)
                    resultLogin.getOrNull()?.let {
                        success("Autenticação bem sucedida!")
                        return@withContext
                    }
                else if (resultLogin.isFailure) {
                    resultLogin.exceptionOrNull()?.let {
                        failure(it)
                        return@withContext
                    }
                }
                failure(RuntimeException("A autenticação falhou de forma inesperada!"))
            }
        }
    }

    // Validators
    fun loginDataChanged(username: EditText, password: EditText) {
        loginDataChanged(username.text.toString(), password.text.toString())
    }
    private fun loginDataChanged(username: String, password: String) {
        val usernameValidation = usernameValidator(username)
        val passwordValidation = passwordValidator(password)
        form.internalValidationError.value = LoginFormErrors(usernameValidation, passwordValidation)
    }
    fun usernameValidator(username: String?): Int? {
        return if (username == null)
        {
            null
        }
        // Validação somente Números
        else if (username.matches("[0-9]*".toRegex()))
        {
            return if (username.trim().length < 3) {
                R.string.invalid_username_digits
            } else if (username.trim().length > 7) {
                R.string.invalid_username_digits
            } else null
        }
        // Validação com Texto
        else
        {
            // Validação para e-mail
            if (username.contains("@"))
            {
                if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                    R.string.invalid_email
                } else null
            }
            // Validação para username
            else
            {
                if (username.trim().isEmpty()) {
                    R.string.invalid_username
                } else null
            }
        }
    }
    fun passwordValidator(password: String?): Int? {
        return if (password == null) {
            null
        } else if (password.trim().length < 8) {
            R.string.invalid_password_digits
        } else if (password.trim().length > 20) {
            R.string.invalid_password_digits
        } else if (password.matches("[^a-z]+".toRegex())) {
            R.string.invalid_password_content_lowercase
        } else if (password.matches("[^A-Z]+".toRegex())) {
            R.string.invalid_password_content_uppercase
        } /*else if (password.matches("[^0-9]+".toRegex())) {
            R.string.invalid_password_content_numbers
        }*/ else null
    }

    companion object {
        internal val repository: AuthRepository = AuthRepository()
    }

    data class LoginFormErrors(@StringRes val usernameError: Int?, @StringRes val passwordError: Int?) {
        fun noHasError(): Boolean {
            return (usernameError==null && passwordError==null)
        }
    }
}
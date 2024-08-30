package com.vitoraguiardf.bobinabanking.ui.main

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.data.entity.JsonWebToken
import com.vitoraguiardf.bobinabanking.data.entity.User
import com.vitoraguiardf.bobinabanking.data.rest.AuthRepository
import com.vitoraguiardf.bobinabanking.utils.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val context: Context): ViewModel<Int, User, Void>() {

    fun me() {
        viewModelScope.launch {
            start()
            withContext(Dispatchers.IO) {
                val result: Result<User> = repository.me()
                if (result.isSuccess)
                    result.getOrNull()?.let {
                        Singleton.instance.user = it
                        success(it)
                        return@withContext
                    }
                else if (result.isFailure) {
                    result.exceptionOrNull()?.let {
                        failure(it)
                        return@withContext
                    }
                }
                failure(RuntimeException(context.getString(R.string.error_operation_has_failed)))
            }
        }
    }

    fun verifySession() {
        viewModelScope.launch {
            start()
            withContext(Dispatchers.IO) {
                var token: JsonWebToken? = null
                Singleton.instance.runDatabase { database ->
                    token = database.jsonWebTokenDao().findLast()
                }
                if (token != null) {
                    Singleton.instance.token = token
                    me()
                } else {
                    failure()
                }
            }
        }
    }

    companion object {
        internal val repository: AuthRepository = AuthRepository()
    }

}
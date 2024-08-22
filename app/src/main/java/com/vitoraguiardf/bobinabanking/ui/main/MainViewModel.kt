package com.vitoraguiardf.bobinabanking.ui.main

import androidx.lifecycle.viewModelScope
import com.vitoraguiardf.bobinabanking.data.entity.User
import com.vitoraguiardf.bobinabanking.data.rest.AuthRepository
import com.vitoraguiardf.bobinabanking.utils.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel<Int, User, Void>() {

    fun me() {
        viewModelScope.launch {
            start()
            withContext(Dispatchers.IO) {
                val result: Result<User> = repository.me()
                if (result.isSuccess)
                    result.getOrNull()?.let {
                        success(it)
                        return@withContext
                    }
                else if (result.isFailure) {
                    result.exceptionOrNull()?.let {
                        failure(it)
                        return@withContext
                    }
                }
                failure(RuntimeException("A autenticação falhou de forma inesperada!"))
            }
        }
    }


    companion object {
        internal val repository: AuthRepository = AuthRepository()
    }

}
package com.vitoraguiardf.bobinabanking.ui.home

import android.content.res.Resources
import androidx.lifecycle.viewModelScope
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.data.entity.User
import com.vitoraguiardf.bobinabanking.data.rest.TransactionRepository
import com.vitoraguiardf.bobinabanking.utils.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserResumeViewModel(private val resources: Resources): ViewModel<Int, User, Void>() {

    fun resume() {
        viewModelScope.launch {
            start()
            withContext(Dispatchers.IO) {
                val result: Result<User> = repository.resume()
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
                failure(RuntimeException(resources.getString(R.string.error_operation_has_failed)))
            }
        }
    }

    companion object {
        internal val repository: TransactionRepository = TransactionRepository()
    }

}
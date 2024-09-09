package com.vitoraguiardf.bobinabanking.ui.transaction.fragments

import android.content.res.Resources
import androidx.lifecycle.viewModelScope
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.data.entity.Account
import com.vitoraguiardf.bobinabanking.data.rest.AccountRepository
import com.vitoraguiardf.bobinabanking.utils.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipientViewModel(private val resources: Resources) : ViewModel<Int, Array<Account>, Void>() {

    fun searchAccounts(email: String) {
        viewModelScope.launch {
            start()
            withContext(Dispatchers.IO) {
                val result: Result<Array<Account>> = accountRepository.search(email)
                if (result.isSuccess)
                    result.getOrNull()?.let { items ->
                        success(items)
                        log("fetched ${items.count()} accounts!")
                        return@withContext
                    }
                else if (result.isFailure) {
                    result.exceptionOrNull()?.let {
                        failure()
                        return@withContext
                    }
                }
                failure(RuntimeException(resources.getString(R.string.error_operation_has_failed)))
            }
        }
    }

    companion object {
        internal val accountRepository = AccountRepository()
    }

}
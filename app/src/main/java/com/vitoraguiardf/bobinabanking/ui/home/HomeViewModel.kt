package com.vitoraguiardf.bobinabanking.ui.home

import androidx.lifecycle.viewModelScope
import com.vitoraguiardf.bobinabanking.data.entity.Transaction
import com.vitoraguiardf.bobinabanking.data.rest.TransactionRepository
import com.vitoraguiardf.bobinabanking.utils.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel: ViewModel<Int, Array<Transaction>, Void>() {

    fun transactions() {
        viewModelScope.launch {
            start()
            withContext(Dispatchers.IO) {
                val result: Result<Array<Transaction>> = repository.transactions()
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
        internal val repository: TransactionRepository = TransactionRepository()
    }

}
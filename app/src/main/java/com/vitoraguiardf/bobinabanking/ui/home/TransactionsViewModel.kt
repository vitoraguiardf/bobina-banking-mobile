package com.vitoraguiardf.bobinabanking.ui.home

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.data.entity.Transaction
import com.vitoraguiardf.bobinabanking.data.rest.TransactionRepository
import com.vitoraguiardf.bobinabanking.utils.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionsViewModel(val context: Context): ViewModel<Int, Array<Transaction>, Void>() {

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
                failure(RuntimeException(context.getString(R.string.error_operation_has_failed)))
            }
        }
    }

    companion object {
        internal val repository: TransactionRepository = TransactionRepository()
    }

}
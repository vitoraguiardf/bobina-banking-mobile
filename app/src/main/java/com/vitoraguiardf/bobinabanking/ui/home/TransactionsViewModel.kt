package com.vitoraguiardf.bobinabanking.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.data.entity.CoilTransaction
import com.vitoraguiardf.bobinabanking.data.entity.CoilTransactionFull
import com.vitoraguiardf.bobinabanking.data.rest.TransactionRepository
import com.vitoraguiardf.bobinabanking.utils.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionsViewModel(val context: Context): ViewModel<Int, List<CoilTransactionFull>?, Void>() {

    fun transactions() {
        viewModelScope.launch {
            start()
            withContext(Dispatchers.IO) {
                val result: Result<Array<CoilTransaction>> = repository.transactions()
                if (result.isSuccess) {
                    result.getOrNull()?.let { items ->
                        var data: List<CoilTransactionFull>? = null
                        Singleton.instance.runDatabase { database ->
                            database.coilTransactionDao().deleteAll()
                            for (item in items){
                                database.coilTransactionDao().save(item)
                            }
                            data = database.coilTransactionDao().findAllFull()
                        }
                        success(data)
                        return@withContext
                    }
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
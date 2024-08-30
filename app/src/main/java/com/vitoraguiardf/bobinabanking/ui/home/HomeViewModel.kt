package com.vitoraguiardf.bobinabanking.ui.home

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.data.entity.TransactionType
import com.vitoraguiardf.bobinabanking.data.rest.TransactionTypeRepository
import com.vitoraguiardf.bobinabanking.utils.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(val context: Context): ViewModel<Int, String, Void>() {

    fun transactionTypes() {
        viewModelScope.launch {
            start()
            withContext(Dispatchers.IO) {
                val result: Result<Array<TransactionType>> = repository.get()
                if (result.isSuccess)
                    result.getOrNull()?.let { items ->
                        Singleton.instance.runDatabase { database ->
                            for (item in items){
                                database.transactionTypeDao().save(item)
                            }
                        }
                        success("Loaded ${items.count()} types!")
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
        internal val repository = TransactionTypeRepository()
    }

}
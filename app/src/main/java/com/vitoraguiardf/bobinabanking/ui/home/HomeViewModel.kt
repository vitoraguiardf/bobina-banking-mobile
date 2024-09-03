package com.vitoraguiardf.bobinabanking.ui.home

import android.content.res.Resources
import androidx.lifecycle.viewModelScope
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.data.entity.Account
import com.vitoraguiardf.bobinabanking.data.entity.CoilTransaction
import com.vitoraguiardf.bobinabanking.data.entity.CoilTransactionFull
import com.vitoraguiardf.bobinabanking.data.entity.TransactionType
import com.vitoraguiardf.bobinabanking.data.rest.AccountRepository
import com.vitoraguiardf.bobinabanking.data.rest.TransactionRepository
import com.vitoraguiardf.bobinabanking.data.rest.TransactionTypeRepository
import com.vitoraguiardf.bobinabanking.utils.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val resources: Resources): ViewModel<Int, List<CoilTransactionFull>?, Void>() {

    fun getData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getAccounts()
                getTransactionTypes()
                getTransactions()
                var data: List<CoilTransactionFull>? = null
                Singleton.instance.runDatabase { database ->
                    data = database.coilTransactionDao().findAllFull()
                }
                success(data)
            }
        }
    }

    private suspend fun getAccounts() {
        start()
        val result: Result<Array<Account>> = accountRepository.get()
        if (result.isSuccess)
            result.getOrNull()?.let { items ->
                Singleton.instance.runDatabase { database ->
                    for (item in items){
                        database.accountDao().save(item)
                    }
                }
                log("fetched ${items.count()} accounts!")
                return
            }
        else if (result.isFailure) {
            result.exceptionOrNull()?.let {
                failure(it)
                return
            }
        }
        failure(RuntimeException(resources.getString(R.string.error_operation_has_failed)))
    }

    private suspend fun getTransactionTypes() {
        start()
        val result: Result<Array<TransactionType>> = typeRepository.get()
        if (result.isSuccess)
            result.getOrNull()?.let { items ->
                Singleton.instance.runDatabase { database ->
                    for (item in items){
                        database.transactionTypeDao().save(item)
                    }
                }
                log("fetched ${items.count()} transaction types!")
                return
            }
        else if (result.isFailure) {
            result.exceptionOrNull()?.let {
                failure(it)
                return
            }
        }
        failure(RuntimeException(resources.getString(R.string.error_operation_has_failed)))
    }

    private suspend fun getTransactions() {
        start()
            val result: Result<Array<CoilTransaction>> = transactionRepository.transactions()
            if (result.isSuccess) {
                result.getOrNull()?.let { items ->
                    Singleton.instance.runDatabase { database ->
                        database.coilTransactionDao().deleteAll()
                        for (item in items){
                            database.coilTransactionDao().save(item)
                        }
                    }
                    log("fetched ${items.count()} transactions!")
                    return
                }
            }
            else if (result.isFailure) {
                result.exceptionOrNull()?.let {
                    failure(it)
                    return
                }
            }
            failure(RuntimeException(resources.getString(R.string.error_operation_has_failed)))
    }

    companion object {
        internal val typeRepository = TransactionTypeRepository()
        internal val accountRepository = AccountRepository()
        internal val transactionRepository: TransactionRepository = TransactionRepository()
    }

}
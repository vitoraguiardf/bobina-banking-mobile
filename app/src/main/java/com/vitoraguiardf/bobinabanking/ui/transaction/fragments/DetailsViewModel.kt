package com.vitoraguiardf.bobinabanking.ui.transaction.fragments

import android.content.res.Resources
import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.data.entity.Account
import com.vitoraguiardf.bobinabanking.utils.viewmodel.ViewModel

class DetailsViewModel(private val resources: Resources) : ViewModel<Int, Array<Account>, Unit>() {
    fun getAccountsExcept(account: Account?) {
        doInBackground {
            var accounts: Array<Account>? = null
            Singleton.instance.runDatabase { database ->
                accounts = when (account) {
                    null -> database.accountDao().findAll()
                    else -> database.accountDao().findAllExcept(account.id)
                }
            }
            accounts?.let { success(it) }
        }
    }
}
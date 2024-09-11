package com.vitoraguiardf.bobinabanking.ui.transaction.fragments

import android.content.res.Resources
import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.data.entity.Account
import com.vitoraguiardf.bobinabanking.utils.viewmodel.ViewModel

class DetailsViewModel(private val resources: Resources) : ViewModel<Int, Array<Account>, Unit>() {
    
    fun getAccounts() {
        doInBackground {
            var accounts: Array<Account>? = null
            Singleton.instance.runDatabase { database ->
                accounts = database.accountDao().findAll()
            }
            accounts?.let { success(it) }
        }
    }

}
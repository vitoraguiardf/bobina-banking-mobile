package com.vitoraguiardf.bobinabanking.ui.transaction

import android.os.Bundle
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.databinding.ActivityCoilTransactionBinding
import com.vitoraguiardf.bobinabanking.ui.transaction.fragments.RecipientFragment
import com.vitoraguiardf.bobinabanking.utils.activities.CustomActivity
import com.vitoraguiardf.bobinabanking.utils.enums.TransactionType

class CoilTransactionActivity : CustomActivity<ActivityCoilTransactionBinding>() {

    override fun viewBindingInflate(): ActivityCoilTransactionBinding {
        return ActivityCoilTransactionBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            when (intent.serializable<TransactionType>("TRANSACTION_TYPE")) {
                TransactionType.USAGE -> {
                    fragmentTransaction.replace(R.id.container, RecipientFragment.newInstance())
                }
                else -> {
                    fragmentTransaction.replace(R.id.container, RecipientFragment.newInstance())
                }
            }
            fragmentTransaction.commitNow()
        }
    }

}
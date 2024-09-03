package com.vitoraguiardf.bobinabanking.ui.transaction

import android.os.Bundle
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.databinding.ActivityCoilTransactionBinding
import com.vitoraguiardf.bobinabanking.ui.transaction.fragments.CoilTransactionFragment
import com.vitoraguiardf.bobinabanking.utils.activities.CustomActivity

class CoilTransactionActivity : CustomActivity<ActivityCoilTransactionBinding>() {

    override fun viewBindingInflate(): ActivityCoilTransactionBinding {
        return ActivityCoilTransactionBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CoilTransactionFragment.newInstance())
                .commitNow()
        }
    }

}
package com.vitoraguiardf.bobinabanking.ui.transaction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.ui.transaction.fragments.CoilTransactionFragment

class CoilTransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coil_transaction)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CoilTransactionFragment.newInstance())
                .commitNow()
        }
    }
}
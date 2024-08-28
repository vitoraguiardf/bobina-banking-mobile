package com.vitoraguiardf.bobinabanking.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.databinding.ActivityHomeBinding
import com.vitoraguiardf.bobinabanking.ui.adapters.TransactionAdapter
import com.vitoraguiardf.bobinabanking.utils.activities.CustomActivity

class HomeActivity : CustomActivity<ActivityHomeBinding>() {
    private lateinit var viewModel: HomeViewModel

    override fun viewBindingInflate(): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.form.throwable.observe(this, Observer {
            val throwable = it?: return@Observer
            throwable.printStackTrace()
            Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
        })

        viewModel.form.result.observe(this, Observer {
            val transactions = it?: return@Observer

            val adapter = TransactionAdapter(this, transactions)

            binding.recyclerViewLancamentos.adapter = adapter

            Toast.makeText(this, "${transactions.size} Transações!", Toast.LENGTH_LONG).show()
        })

        binding.textViewUserName.text = Singleton.instance.user.name
        binding.textViewSaldo.text = "75 bobinas"

        viewModel.transactions()
    }

}
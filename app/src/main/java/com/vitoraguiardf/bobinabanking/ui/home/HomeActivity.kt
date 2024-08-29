package com.vitoraguiardf.bobinabanking.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.databinding.ActivityHomeBinding
import com.vitoraguiardf.bobinabanking.ui.adapters.TransactionAdapter
import com.vitoraguiardf.bobinabanking.utils.activities.CustomActivity
import com.vitoraguiardf.bobinabanking.utils.viewmodel.FormState

class HomeActivity : CustomActivity<ActivityHomeBinding>() {
    private lateinit var viewModel: TransactionsViewModel

    override fun viewBindingInflate(): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TransactionsViewModel::class.java]
        viewModel.form.state.observe(this, Observer {
            val state = it?: return@Observer
            binding.included.loading.visibility = when(state) {
                FormState.RUNNING -> View.VISIBLE
                else -> View.GONE
            }
            binding.recyclerViewLancamentos.visibility = when(state) {
                FormState.SUCCESS -> View.VISIBLE
                else -> View.GONE
            }
        })
        viewModel.form.throwable.observe(this, Observer {
            val throwable = it?: return@Observer
            Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
        })
        viewModel.form.result.observe(this, Observer {
            val transactions = it?: return@Observer
            val adapter = TransactionAdapter(this, transactions)
            binding.recyclerViewLancamentos.adapter = adapter
        })

        binding.textViewUserName.text = Singleton.instance.user.name
        val saldo = (Singleton.instance.user.sumOfToTransactions?: 0) - (Singleton.instance.user.sumOfToTransactions?: 0)
        binding.textViewSaldo.text = "$saldo bobinas"

        viewModel.transactions()
    }

}
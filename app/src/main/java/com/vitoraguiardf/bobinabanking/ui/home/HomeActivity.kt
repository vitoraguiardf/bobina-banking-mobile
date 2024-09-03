package com.vitoraguiardf.bobinabanking.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.Singleton
import com.vitoraguiardf.bobinabanking.databinding.ActivityHomeBinding
import com.vitoraguiardf.bobinabanking.ui.ViewModelFactory
import com.vitoraguiardf.bobinabanking.ui.adapters.TransactionAdapter
import com.vitoraguiardf.bobinabanking.utils.activities.CustomActivity
import com.vitoraguiardf.bobinabanking.utils.viewmodel.FormState
import java.util.Locale

class HomeActivity : CustomActivity<ActivityHomeBinding>() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var vmUserResume: UserResumeViewModel

    override fun viewBindingInflate(): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelProvider = ViewModelProvider(this, ViewModelFactory(this))

        viewModel = viewModelProvider[HomeViewModel::class.java]
        viewModel.form.state.observe(this, Observer {
            val state = it?: return@Observer
            binding.included.loading.visibility = when(state) {
                FormState.RUNNING -> View.VISIBLE
                else -> View.GONE
            }
            binding.recyclerViewLancamentos.visibility = when(state) {
                FormState.RUNNING -> View.GONE
                else -> View.VISIBLE
            }
        })
        viewModel.form.throwable.observe(this, Observer {
            val throwable = it?: return@Observer
            Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
        })
        viewModel.form.result.observe(this, Observer {
            val result = it?: return@Observer
            val adapter = TransactionAdapter(this, result.toTypedArray())
            binding.recyclerViewLancamentos.adapter = adapter
        })

        vmUserResume = viewModelProvider[UserResumeViewModel::class.java]
        vmUserResume.form.state.observe(this, Observer {
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
        vmUserResume.form.throwable.observe(this, Observer {
            val throwable = it?: return@Observer
            Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
        })
        vmUserResume.form.result.observe(this, Observer {
            val user = it?: return@Observer
            binding.textViewSaldo.text = String.format(Locale.getDefault(),
                "%,2d %s",
                ((user.sumOfToTransactions?: 0) - (user.sumOfFromTransactions?: 0)),
                getString(R.string.unities)
            )
        })

        binding.textViewUserName.text = Singleton.instance.user.name

        vmUserResume.resume()
        viewModel.getData()
    }

    override fun onPostResume() {
        super.onPostResume()
        viewModel.getData()
    }
}
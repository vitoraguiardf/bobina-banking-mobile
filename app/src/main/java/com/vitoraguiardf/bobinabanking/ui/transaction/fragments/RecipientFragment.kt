package com.vitoraguiardf.bobinabanking.ui.transaction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vitoraguiardf.bobinabanking.databinding.FragmentRecipientBinding
import com.vitoraguiardf.bobinabanking.ui.ViewModelFactory
import com.vitoraguiardf.bobinabanking.ui.adapters.AccountAdapterRecycler
import com.vitoraguiardf.bobinabanking.ui.transaction.SharedViewModel
import com.vitoraguiardf.bobinabanking.ui.transaction.TransferenceScenarios
import com.vitoraguiardf.bobinabanking.utils.FastMessages
import com.vitoraguiardf.bobinabanking.utils.viewmodel.FormState

class RecipientFragment : Fragment() {

    companion object {
        fun newInstance() = RecipientFragment()
    }

    private lateinit var binding: FragmentRecipientBinding
    private lateinit var viewModel: RecipientViewModel
    private lateinit var sharedModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelProvider = ViewModelProvider(requireActivity(), ViewModelFactory(resources))
        viewModel = viewModelProvider[RecipientViewModel::class]
        sharedModel = viewModelProvider[SharedViewModel::class]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipientBinding.inflate(inflater, container, false)

        binding.buttonSearch.setOnClickListener {
            viewModel.searchAccounts(binding.editTextDestinKey.text.toString())
//            sharedModel.transferenceForm.setRecipient("RECIPIENT ACCOUNT")
//            binding.included.loading.visibility = View.VISIBLE
        }
        binding.buttonScan.setOnClickListener {
            sharedModel.transferenceForm.setScenario(TransferenceScenarios.TRANSFERENCE_QRCODE)
        }
        viewModel.form.state.observe(viewLifecycleOwner, Observer {
            val state = it?: return@Observer
            binding.included.loading.visibility = when(state) {
                FormState.RUNNING -> View.VISIBLE
                else -> View.GONE
            }
            binding.listItems.visibility = when(state) {
                FormState.RUNNING -> View.GONE
                else -> View.VISIBLE
            }
        })
        viewModel.form.throwable.observe(viewLifecycleOwner, Observer {
            val throwable = it?: return@Observer
            FastMessages.error(requireContext(), throwable.message)
        })
        viewModel.form.result.observe(viewLifecycleOwner, Observer {
            val items = it?:return@Observer
            val adapter = AccountAdapterRecycler(requireContext(), items)
            binding.listItems.adapter = adapter
        })

        return binding.root
    }

}
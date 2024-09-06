package com.vitoraguiardf.bobinabanking.ui.transaction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vitoraguiardf.bobinabanking.databinding.FragmentRecipientBinding
import com.vitoraguiardf.bobinabanking.ui.ViewModelFactory
import com.vitoraguiardf.bobinabanking.ui.transaction.SharedViewModel
import com.vitoraguiardf.bobinabanking.ui.transaction.TransferenceScenarios

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
            sharedModel.transferenceForm.setRecipient("RECIPIENT ACCOUNT")
//            binding.included.loading.visibility = View.VISIBLE
        }
        binding.buttonScan.setOnClickListener {
            sharedModel.transferenceForm.setScenario(TransferenceScenarios.TRANSFERENCE_QRCODE)
        }
        binding.included.loading.setOnClickListener {
            binding.included.loading.visibility = View.GONE
        }

        return binding.root
    }

}
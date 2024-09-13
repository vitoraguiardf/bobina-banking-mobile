package com.vitoraguiardf.bobinabanking.ui.transaction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.databinding.FragmentConfirmationBinding
import com.vitoraguiardf.bobinabanking.ui.ViewModelFactory
import com.vitoraguiardf.bobinabanking.ui.transaction.SharedViewModel
import com.vitoraguiardf.bobinabanking.ui.transaction.TransferenceScenarios

class ConfirmationFragment : Fragment() {

    companion object {
        fun newInstance() = ConfirmationFragment()
    }

    private lateinit var binding: FragmentConfirmationBinding
    private lateinit var viewModel: ConfirmationViewModel
    private lateinit var sharedModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelProvider = ViewModelProvider(requireActivity(), ViewModelFactory(resources))
        viewModel = viewModelProvider[ConfirmationViewModel::class]
        sharedModel = viewModelProvider[SharedViewModel::class]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfirmationBinding.inflate(inflater, container, false)
        val scenario = sharedModel.transferenceForm.scenario.value
        val senderAccount = sharedModel.transferenceForm.sender.value
        val recipientAccount = sharedModel.transferenceForm.recipient.value
        val quantity = sharedModel.transferenceForm.quantity.value
        val description = sharedModel.transferenceForm.description.value
        binding.recipientContent.visibility = when (scenario) {
            TransferenceScenarios.USAGE -> {
                binding.transactionName.text = getString(R.string.usage)
                View.GONE
            }
            else -> {
                binding.transactionName.text = getString(R.string.transference)
                recipientAccount?.let {
                    binding.recipientUsername.text = it.holderName.uppercase()
                    binding.recipientAccountName.text = it.name.uppercase()
                    binding.recipientAccountKey.text = it.name.uppercase()
                }
                View.VISIBLE
            }
        }
        binding.quantity.text = String.format("%s %s",
            quantity?: throw IllegalArgumentException()
            , getString(R.string.unities))
        binding.descriptionContent.visibility =
            if (description.isNullOrEmpty()) {
                View.GONE
            } else {
                binding.description.text = description
                View.VISIBLE
            }
        when (senderAccount) {
            null -> throw IllegalArgumentException(getString(R.string.business_logic_has_broken))
            else -> {
                binding.senderUsername.text = senderAccount.holderName
                binding.senderAccountName.text = senderAccount.name
            }
        }
        return binding.root
    }
}
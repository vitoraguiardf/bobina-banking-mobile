package com.vitoraguiardf.bobinabanking.ui.transaction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vitoraguiardf.bobinabanking.databinding.FragmentDetailsBinding
import com.vitoraguiardf.bobinabanking.ui.ViewModelFactory
import com.vitoraguiardf.bobinabanking.ui.transaction.SharedViewModel

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsViewModel
    private lateinit var sharedModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelProvider = ViewModelProvider(requireActivity(), ViewModelFactory(resources))
        viewModel = viewModelProvider[DetailsViewModel::class]
        sharedModel = viewModelProvider[SharedViewModel::class]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recipientAccount = sharedModel.transferenceForm.recipient.value
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        recipientAccount?.let {
            binding.cardAccount.username.text = it.holderName
            binding.cardAccount.account.text = it.name
        }
        binding.buttonConfirm.setOnClickListener {
            try {
                val description: String = binding.inputDescription.text.toString()
                sharedModel.transferenceForm.setDescription(description)
            } catch (exception: IllegalArgumentException) {
                binding.inputDescription.error = exception.message
            }
            try {
                val quantity: String = binding.inputQuantity.text.toString()
                sharedModel.transferenceForm.setQuantity(when {
                    quantity.isEmpty() -> throw IllegalArgumentException("Informe a quantidade")
                    quantity.matches(Regex("^$[0-9]*")) -> throw IllegalArgumentException("Valor inválido")
                    else -> quantity.toInt()
                })
            } catch (exception: IllegalArgumentException) {
                binding.inputQuantity.error = exception.message
            }
        }
        return binding.root
    }
}
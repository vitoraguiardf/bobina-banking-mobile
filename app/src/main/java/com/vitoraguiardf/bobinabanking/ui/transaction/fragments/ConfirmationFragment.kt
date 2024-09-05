package com.vitoraguiardf.bobinabanking.ui.transaction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vitoraguiardf.bobinabanking.databinding.FragmentConfirmationBinding
import com.vitoraguiardf.bobinabanking.ui.ViewModelFactory
import com.vitoraguiardf.bobinabanking.ui.transaction.SharedViewModel

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
        return binding.root
    }
}
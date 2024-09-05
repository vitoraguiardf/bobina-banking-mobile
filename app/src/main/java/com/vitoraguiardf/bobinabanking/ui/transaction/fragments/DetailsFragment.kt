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
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}
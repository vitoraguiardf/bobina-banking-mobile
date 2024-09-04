package com.vitoraguiardf.bobinabanking.ui.transaction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vitoraguiardf.bobinabanking.databinding.FragmentSenderBinding

class SenderFragment : Fragment() {

    companion object {
        fun newInstance() = SenderFragment()
    }

    private lateinit var binding: FragmentSenderBinding
    private val viewModel: SenderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSenderBinding.inflate(inflater, container, false)
        return binding.root
    }
}
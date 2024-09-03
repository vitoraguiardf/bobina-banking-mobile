package com.vitoraguiardf.bobinabanking.ui.transaction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vitoraguiardf.bobinabanking.databinding.FragmentMainBinding
import com.vitoraguiardf.bobinabanking.ui.ViewModelFactory

class CoilTransactionFragment : Fragment() {

    companion object {
        fun newInstance() = CoilTransactionFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: CoilTransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelProvider = ViewModelProvider(this, ViewModelFactory(resources))
        viewModel = viewModelProvider[CoilTransactionViewModel::class]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.buttonSearch.setOnClickListener {
            binding.included.loading.visibility = View.VISIBLE
        }
        binding.included.loading.setOnClickListener {
            binding.included.loading.visibility = View.GONE
        }

        return binding.root
    }

}
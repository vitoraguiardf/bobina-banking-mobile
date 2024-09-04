package com.vitoraguiardf.bobinabanking.ui.transaction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vitoraguiardf.bobinabanking.databinding.FragmentRecipientBinding
import com.vitoraguiardf.bobinabanking.ui.ViewModelFactory

class RecipientFragment : Fragment() {

    companion object {
        fun newInstance() = RecipientFragment()
    }

    private lateinit var binding: FragmentRecipientBinding
    private lateinit var viewModel: RecipientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelProvider = ViewModelProvider(this, ViewModelFactory(resources))
        viewModel = viewModelProvider[RecipientViewModel::class]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipientBinding.inflate(inflater, container, false)

        binding.buttonSearch.setOnClickListener {
            binding.included.loading.visibility = View.VISIBLE
        }
        binding.included.loading.setOnClickListener {
            binding.included.loading.visibility = View.GONE
        }

        return binding.root
    }

}
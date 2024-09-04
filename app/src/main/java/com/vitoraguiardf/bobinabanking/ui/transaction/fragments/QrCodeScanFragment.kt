package com.vitoraguiardf.bobinabanking.ui.transaction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vitoraguiardf.bobinabanking.databinding.FragmentQrCodeScanBinding

class QrCodeScanFragment : Fragment() {

    companion object {
        fun newInstance() = QrCodeScanFragment()
    }

    private lateinit var binding: FragmentQrCodeScanBinding
    private val viewModel: QrCodeScanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQrCodeScanBinding.inflate(inflater, container, false)
        return binding.root
    }
}
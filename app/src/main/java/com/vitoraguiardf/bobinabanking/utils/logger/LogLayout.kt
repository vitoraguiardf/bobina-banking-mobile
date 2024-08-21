package com.vitoraguiardf.bobinabanking.utils.logger

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.vitoraguiardf.bobinabanking.databinding.LayoutLoggerBinding

class LogLayout(context: Context, attrs: AttributeSet?):
    androidx.constraintlayout.widget.ConstraintLayout(context, attrs) {

    private val binding = LayoutLoggerBinding.inflate(
            LayoutInflater.from(context),this,true)

    init {
        binding.current.setOnLongClickListener { _ -> binding.console.visibility = View.VISIBLE; true }
        binding.close.setOnClickListener{ _ -> binding.console.visibility = View.GONE }
        binding.current.text = "Bem vindo!"
    }
}
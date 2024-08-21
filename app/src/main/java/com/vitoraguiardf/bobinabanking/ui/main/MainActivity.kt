package com.vitoraguiardf.bobinabanking.ui.main

import com.vitoraguiardf.bobinabanking.AbstractCustomActivity
import com.vitoraguiardf.bobinabanking.databinding.ActivityMainBinding

class MainActivity : AbstractCustomActivity<ActivityMainBinding>() {
    private lateinit var viewModel: MainViewModel
    override fun viewBindingInflate(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}
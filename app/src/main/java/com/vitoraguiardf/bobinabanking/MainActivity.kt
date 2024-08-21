package com.vitoraguiardf.bobinabanking

import com.vitoraguiardf.bobinabanking.databinding.ActivityMainBinding

class MainActivity : AbstractCustomActivity<ActivityMainBinding>() {
    private lateinit var viewModel: MainViewModel
    override fun viewBindingInflate(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}
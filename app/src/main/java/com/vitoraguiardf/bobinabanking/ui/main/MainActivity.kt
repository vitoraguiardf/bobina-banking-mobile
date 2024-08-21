package com.vitoraguiardf.bobinabanking.ui.main

import com.vitoraguiardf.bobinabanking.utils.activities.CustomActivity
import com.vitoraguiardf.bobinabanking.databinding.ActivityMainBinding

class MainActivity : CustomActivity<ActivityMainBinding>() {
    private lateinit var viewModel: MainViewModel
    override fun viewBindingInflate(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}
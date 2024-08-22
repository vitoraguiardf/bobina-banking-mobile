package com.vitoraguiardf.bobinabanking.ui.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.vitoraguiardf.bobinabanking.databinding.ActivityHomeBinding
import com.vitoraguiardf.bobinabanking.utils.activities.CustomActivity

class HomeActivity : CustomActivity<ActivityHomeBinding>() {
    private lateinit var viewModel: HomeViewModel

    override fun viewBindingInflate(): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

}
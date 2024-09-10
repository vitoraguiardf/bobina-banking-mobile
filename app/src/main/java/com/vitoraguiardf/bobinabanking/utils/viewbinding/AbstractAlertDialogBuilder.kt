package com.vitoraguiardf.bobinabanking.utils.viewbinding

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding

abstract class AbstractAlertDialogBuilder<ViewBinder: ViewBinding>(context: Context) :
    AlertDialog.Builder(context) {

    var binding: ViewBinder

    init {
        binding = this.viewBindingInflate()
        this.setView(binding.root)
    }

    abstract fun viewBindingInflate(): ViewBinder

}

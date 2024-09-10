package com.vitoraguiardf.bobinabanking.utils.viewbinding

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding

abstract class AbstractAlertDialogBuilder<ViewBinder: ViewBinding>: AlertDialog.Builder {

    var bindind: ViewBinder

    constructor(context: Context) : super(context) {
        bindind = viewBindingInflate()
        this.setView(bindind.root)
    }

    abstract fun viewBindingInflate(): ViewBinder

    override fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener?): AlertDialog.Builder {
        return super.setOnDismissListener(onDismissListener)
    }

}

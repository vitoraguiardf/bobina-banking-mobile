package com.vitoraguiardf.bobinabanking.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.vitoraguiardf.bobinabanking.R

class FastMessages(context: Context): AlertDialog(context) {
    companion object {
        fun success(context: Context?, message: String?) {
            Builder(context!!)
                .setTitle(message)
                .setMessage(getMessage(""))
                .setIcon(R.drawable.ic_baseline_check_circle_24)
                .setNegativeButton("Ok") { dialog: DialogInterface, _: Int -> dialog.cancel() }
                .show()
        }
        fun success(context: Context?, title: String?, message: String) {
            Builder(context!!)
                .setTitle(title)
                .setMessage(getMessage(message))
                .setIcon(R.drawable.ic_baseline_check_circle_24)
                .setNegativeButton("Ok") { dialog: DialogInterface, _: Int -> dialog.cancel() }
                .show()
        }
        fun warning(context: Context?, message: String?) {
            Builder(context!!)
                .setTitle(message)
                .setMessage(getMessage(""))
                .setIcon(R.drawable.ic_baseline_warning_24)
                .setNegativeButton("Ok") { dialog: DialogInterface, _: Int -> dialog.cancel() }
                .show()
        }
        fun warning(context: Context?, title: String?, message: String) {
            Builder(context!!)
                .setTitle(title)
                .setMessage(getMessage(message))
                .setIcon(R.drawable.ic_baseline_warning_24)
                .setNegativeButton("Ok") { dialog: DialogInterface, _: Int -> dialog.cancel() }
                .show()
        }
        fun warning(
            context: Context?,
            title: String?,
            format: String?,
            vararg args: Any?
        ) {
            Builder(context!!)
                .setTitle(title)
                .setMessage(getMessage(String.format(format!!, *args)))
                .setIcon(R.drawable.ic_baseline_warning_24)
                .setNegativeButton("Ok") { dialog: DialogInterface, _: Int -> dialog.cancel() }
                .show()
        }
        fun error(context: Context?, message: String?) {
            Builder(context!!)
                .setMessage(getMessage(message))
                .setIcon(R.drawable.ic_baseline_error_24)
                .setNegativeButton("Ok"){ dialog: DialogInterface, _: Int -> dialog.cancel() }
                .show()
        }
        fun error(context: Context?, title: String?, message: String) {
            Builder(context!!)
                .setTitle(title)
                .setMessage(getMessage(message))
                .setIcon(R.drawable.ic_baseline_error_24)
                .setNegativeButton("Ok") { dialog: DialogInterface, _: Int -> dialog.cancel() }
                .show()
        }
        fun error(
            context: Context?,
            title: String?,
            message: String,
            onDismiss: DialogInterface.OnDismissListener?
        ) {
            Builder(context!!)
                .setTitle(title)
                .setMessage(getMessage(message))
                .setIcon(R.drawable.ic_baseline_error_24)
                .setNegativeButton("Ok") { dialog: DialogInterface, _: Int -> dialog.cancel() }
                .setOnDismissListener(onDismiss)
                .show()
        }
        fun confirm(context: Context?, message: String?, onConfirm: DialogInterface.OnClickListener?) {
            Builder(context!!)
                .setTitle(message)
                .setMessage(getMessage(""))
                .setIcon(R.drawable.ic_baseline_help_24)
                .setPositiveButton("Sim", onConfirm)
                .setNegativeButton("Não") { dialog: DialogInterface, _: Int -> dialog.cancel() }
                .show()
        }
        fun confirm(
            context: Context?,
            title: String?,
            message: String,
            onConfirm: DialogInterface.OnClickListener?
        ) {
            Builder(context!!)
                .setTitle(title)
                .setMessage(getMessage(message))
                .setIcon(R.drawable.ic_baseline_help_24)
                .setPositiveButton("Sim", onConfirm)
                .setNegativeButton("Não") { dialog: DialogInterface, _: Int -> dialog.cancel() }
                .show()
        }
        fun confirm(
            context: Context?,
            title: String?,
            message: String,
            onConfirm: DialogInterface.OnClickListener?,
            onCancel: DialogInterface.OnClickListener?
        ) {
            Builder(context!!)
                .setTitle(title)
                .setMessage(getMessage(message))
                .setIcon(R.drawable.ic_baseline_help_24)
                .setPositiveButton("Sim", onConfirm)
                .setNegativeButton("Não", onCancel)
                .show()
        }
        private fun getMessage(message: String?): String {
            return message!!
        }
    }
}
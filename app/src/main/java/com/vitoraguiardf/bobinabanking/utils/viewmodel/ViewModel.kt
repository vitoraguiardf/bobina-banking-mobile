package com.vitoraguiardf.bobinabanking.utils.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class ViewModel<Progress, Result, FormValidator>(
    val form: Form<Progress, Result, FormValidator> = Form()
): androidx.lifecycle.ViewModel() {

    protected suspend fun start () {
        log("STARTED")
        dispatchMain {
            this@ViewModel.form.internalState.value = FormState.RUNNING
        }
    }

    protected suspend fun success (result: Result) {
        log("SUCCESS")
        dispatchMain {
            this@ViewModel.form.internalState.value = FormState.SUCCESS
            this@ViewModel.form.internalResult.value = result
        }
    }

    protected suspend fun failure (throwable: Throwable) {
        log("FAILED", throwable.message.toString(), throwable.stackTraceToString())
        dispatchMain {
            this@ViewModel.form.internalState.value = FormState.FAILED
            this@ViewModel.form.internalThrowable.value = throwable
        }
    }

    protected suspend fun log (vararg messages: String) {
        dispatchMain {
            for (message in messages) {
                this@ViewModel.form.internalLog.value = message
            }
        }
    }

    fun dispatchMain (block: () -> Unit) {
        viewModelScope.launch (Dispatchers.Main) {
            block()
        }
    }

}
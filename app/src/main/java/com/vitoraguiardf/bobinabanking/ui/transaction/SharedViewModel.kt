package com.vitoraguiardf.bobinabanking.ui.transaction

import android.content.res.Resources
import com.vitoraguiardf.bobinabanking.R
import com.vitoraguiardf.bobinabanking.data.entity.Message
import com.vitoraguiardf.bobinabanking.data.rest.Transaction
import com.vitoraguiardf.bobinabanking.data.rest.TransactionRepository
import com.vitoraguiardf.bobinabanking.utils.viewmodel.ViewModel

class SharedViewModel(private val resources: Resources): ViewModel<Int, Message, Unit>() {
    val transferenceForm: TransferenceForm = TransferenceForm()
    fun postTransaction() {
        val requestData = validate()
        doInBackground {
            val result = restRepository.post(requestData)
            if (result.isSuccess)
                result.getOrNull()?.let {
                    success(it)
                    return@doInBackground
                }
            else if (result.isFailure) {
                result.exceptionOrNull()?.let {
                    failure(it)
                    return@doInBackground
                }
            }
            failure(RuntimeException(resources.getString(R.string.error_operation_has_failed)))
        }
    }

    private fun validate(): Transaction {
        return Transaction(
            transactionTypeId = 2,
            fromStorageId = transferenceForm.sender.value?.id,
            toStorageId = transferenceForm.recipient.value?.id,
            description = transferenceForm.description.value,
            quantity = transferenceForm.quantity.value?: 0,
        )
    }

    companion object {
        val restRepository = TransactionRepository()
    }
}
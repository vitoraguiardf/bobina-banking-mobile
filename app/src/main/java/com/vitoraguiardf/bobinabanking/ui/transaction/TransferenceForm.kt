package com.vitoraguiardf.bobinabanking.ui.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vitoraguiardf.bobinabanking.data.entity.Account
import com.vitoraguiardf.bobinabanking.data.rest.Transaction

data class TransferenceForm(
    // Scenario
    private val internalScenario: MutableLiveData<TransferenceScenarios> = MutableLiveData<TransferenceScenarios>(),
    val scenario: LiveData<TransferenceScenarios> = internalScenario,
    // Type
    private val internalType: MutableLiveData<Int?> = MutableLiveData<Int?>(null),
    val type: LiveData<Int?> = internalType,
    // Sender
    private val internalSender: MutableLiveData<Account?> = MutableLiveData<Account?>(null),
    val sender: LiveData<Account?> = internalSender,
    // Recipient
    private val internalRecipient: MutableLiveData<Account?> = MutableLiveData<Account?>(null),
    val recipient: LiveData<Account?> = internalRecipient,
    // Quantity
    private val internalQuantity: MutableLiveData<Int?> = MutableLiveData<Int?>(null),
    val quantity: LiveData<Int?> = internalQuantity,
    // Description
    private val internalDescription: MutableLiveData<String?> = MutableLiveData<String?>(null),
    val description: LiveData<String?> = internalDescription,
    // Confirmation
    private val internalConfirmation: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false),
    val confirmation: LiveData<Boolean> = internalConfirmation,
) {
    fun setScenario(scenario: TransferenceScenarios) {
        internalScenario.value = scenario
    }
    fun setType(type: Int?) {
        internalType.value = type
    }
    fun setSender(sender: Account?) {
        internalSender.value = sender
    }
    fun setRecipient(recipient: Account?) {
        internalRecipient.value = recipient
    }
    fun setQuantity(quantity: Int?) {
        internalQuantity.value = quantity
    }
    fun setDescription(description: String?) {
        internalDescription.value = description
    }
    fun confirm(confirm: Boolean = true) {
        internalConfirmation.value = confirm
    }
    fun transaction(): Transaction {
        return Transaction(
            transactionTypeId = internalType.value!!,
            fromStorageId = internalSender.value?.id,
            toStorageId = internalRecipient.value?.id,
            description = internalDescription.value,
            quantity = internalQuantity.value?: 0,
        )
    }
}
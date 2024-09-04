package com.vitoraguiardf.bobinabanking.ui.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class TransferenceForm(
    // Scenario
    internal val internalScenario: MutableLiveData<TransferenceScenarios> = MutableLiveData<TransferenceScenarios>(),
    val scenario: LiveData<TransferenceScenarios> = internalScenario,
    // Type
    internal val internalType: MutableLiveData<String?> = MutableLiveData<String?>(null),
    val type: LiveData<String?> = internalType,
    // Sender
    internal val internalSender: MutableLiveData<String?> = MutableLiveData<String?>(null),
    val sender: LiveData<String?> = internalSender,
    // Recipient
    internal val internalRecipient: MutableLiveData<String?> = MutableLiveData<String?>(null),
    val recipient: LiveData<String?> = internalRecipient,
    // Confirmation
    internal val internalConfirmation: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false),
    val confirmation: LiveData<Boolean> = internalConfirmation,
) {
    fun setScenario(scenario: TransferenceScenarios) {
        internalScenario.value = scenario
    }
    fun setType(type: String?) {
        internalType.value = type
    }
    fun setSender(sender: String?) {
        internalSender.value = sender
    }
    fun setRecipient(recipient: String?) {
        internalRecipient.value = recipient
    }
    fun confirm(confirm: Boolean = true) {
        internalConfirmation.value = confirm
    }
}
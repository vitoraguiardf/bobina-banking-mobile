package com.vitoraguiardf.bobinabanking.utils.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class Form<Progress, Result, FormValidator>(
    // Log
    internal val internalLog: MutableLiveData<String> = MutableLiveData<String>(),
    val log: LiveData<String> = internalLog,
    // State
    internal val internalState: MutableLiveData<FormState> = MutableLiveData<FormState>(),
    val state: LiveData<FormState> = internalState,
    // Throwable
    internal val internalThrowable: MutableLiveData<Throwable> = MutableLiveData<Throwable>(),
    val throwable: LiveData<Throwable> = internalThrowable,
    // Validator
    internal val internalValidationError: MutableLiveData<FormValidator> = MutableLiveData<FormValidator>(),
    val validationError: LiveData<FormValidator> = internalValidationError,
    // Progress
    internal val internalProgress: MutableLiveData<Progress> = MutableLiveData<Progress>(),
    val progress: LiveData<Progress> = internalProgress,
    // Result
    internal val internalResult: MutableLiveData<Result> = MutableLiveData<Result>(),
    val result: LiveData<Result> = internalResult,
)
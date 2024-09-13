package com.vitoraguiardf.bobinabanking.data.rest

import com.google.gson.annotations.SerializedName

data class Transaction(
    val description: String?,
    @SerializedName("transaction_type_id")
    val transactionTypeId: Int,
    @SerializedName("from_storage_id")
    val fromStorageId: Int? = null,
    @SerializedName("to_storage_id")
    val toStorageId: Int? = null,
    val quantity: Int,
)

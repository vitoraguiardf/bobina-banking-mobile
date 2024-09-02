package com.vitoraguiardf.bobinabanking.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CoilTransaction(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val description: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("creator_user_id")
    val creatorUserId: Int,
    @SerializedName("transaction_type_id")
    val transactionTypeId: Int,
    @SerializedName("from_storage_id")
    val fromStorageId: Int? = null,
    @SerializedName("to_storage_id")
    val toStorageId: Int? = null,
    val quantity: Int,
) {

}
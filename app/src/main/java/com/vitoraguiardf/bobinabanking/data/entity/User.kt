package com.vitoraguiardf.bobinabanking.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val email: String,
    val profileImage: String,
    @SerializedName("from_transactions_sum_quantity")
    val sumOfFromTransactions: Int?,
    @SerializedName("to_transactions_sum_quantity")
    val sumOfToTransactions: Int?,
)
package com.vitoraguiardf.bobinabanking.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Account(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    @SerializedName("holder_name")
    val holderName: String,
) {
    override fun toString(): String {
        return "$id: $name"
    }
}
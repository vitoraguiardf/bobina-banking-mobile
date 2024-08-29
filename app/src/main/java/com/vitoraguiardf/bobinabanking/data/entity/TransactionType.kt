package com.vitoraguiardf.bobinabanking.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionType(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val description: String,
)

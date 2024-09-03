package com.vitoraguiardf.bobinabanking.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CoilTransactionFull(
    @Embedded
    val coilTransaction: CoilTransaction,
    @Relation(
        parentColumn = "transactionTypeId",
        entityColumn = "id",
    )
    val transactionType: TransactionType,
    @Relation(
        parentColumn = "fromStorageId",
        entityColumn = "id",
    )
    val fromAccount: Account?,
    @Relation(
        parentColumn = "toStorageId",
        entityColumn = "id",
    )
    val toAccount: Account?,
)
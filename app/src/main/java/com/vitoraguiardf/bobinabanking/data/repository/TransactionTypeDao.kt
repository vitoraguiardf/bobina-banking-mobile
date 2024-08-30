package com.vitoraguiardf.bobinabanking.data.repository

import androidx.room.Dao
import androidx.room.Query
import com.vitoraguiardf.bobinabanking.data.entity.TransactionType

@Dao
interface TransactionTypeDao: DaoRepository<TransactionType, Int> {
    @Query("SELECT * FROM TransactionType")
    fun findAll(): List<TransactionType>
    @Query("SELECT * FROM TransactionType ORDER BY ID DESC LIMIT 1")
    fun findLast(): TransactionType?
    @Query("SELECT * FROM TransactionType WHERE id IN (:ids)")
    fun findAllById(ids: IntArray): List<TransactionType>
}
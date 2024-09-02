package com.vitoraguiardf.bobinabanking.data.repository

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.vitoraguiardf.bobinabanking.data.entity.CoilTransaction
import com.vitoraguiardf.bobinabanking.data.entity.CoilTransactionFull

@Dao
interface CoilTransactionDao: DaoRepository<CoilTransaction, Int> {
    @Query("DELETE FROM CoilTransaction")
    fun deleteAll()

    @Transaction
    @Query("SELECT * FROM CoilTransaction")
    fun findAll(): List<CoilTransaction>

    @Transaction
    @Query("SELECT * FROM CoilTransaction")
    fun findAllFull(): List<CoilTransactionFull>

    @Query("SELECT * FROM CoilTransaction ORDER BY ID DESC LIMIT 1")
    fun findLast(): CoilTransaction?
    @Query("SELECT * FROM CoilTransaction WHERE id IN (:ids)")
    fun findAllById(ids: IntArray): List<CoilTransaction>
}
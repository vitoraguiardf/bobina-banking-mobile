package com.vitoraguiardf.bobinabanking.data.repository

import androidx.room.Dao
import androidx.room.Query
import com.vitoraguiardf.bobinabanking.data.entity.JsonWebToken

@Dao
interface JsonWebTokenDao: DaoRepository<JsonWebToken, Int> {
    @Query("SELECT * FROM JsonWebToken")
    fun findAll(): List<JsonWebToken>
    @Query("SELECT * FROM JsonWebToken ORDER BY ID DESC LIMIT 1")
    fun findLast(): JsonWebToken?
    @Query("SELECT * FROM JsonWebToken WHERE id IN (:ids)")
    fun findAllById(ids: IntArray): List<JsonWebToken>
}
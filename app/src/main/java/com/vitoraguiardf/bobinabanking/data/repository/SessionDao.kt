package com.vitoraguiardf.bobinabanking.data.repository

import androidx.room.Dao
import androidx.room.Query
import com.vitoraguiardf.bobinabanking.data.entity.Session
import com.vitoraguiardf.bobinabanking.data.entity.User

@Dao
interface SessionDao: DaoRepository<Session, Int> {
    @Query("SELECT * FROM Session")
    suspend fun findAll(): List<User>
    @Query("SELECT * FROM Session WHERE id IN (:ids)")
    suspend fun findAllById(ids: IntArray): List<Session>
}
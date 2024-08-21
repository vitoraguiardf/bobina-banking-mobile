package com.vitoraguiardf.bobinabanking.data.repository

import androidx.room.Dao
import androidx.room.Query
import com.vitoraguiardf.bobinabanking.data.entity.User

@Dao
interface UserDao: DaoRepository<User, Int> {
    @Query("SELECT * FROM User")
    fun findAll(): List<User>
    @Query("SELECT * FROM User WHERE id = :id")
    fun findById(id: Int): List<User>
    @Query("SELECT * FROM User WHERE id IN (:ids)")
    fun findAllById(ids: List<Int>): List<User>
    @Query("DELETE FROM User")
    fun deleteAll()
}
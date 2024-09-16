package com.vitoraguiardf.bobinabanking.data.repository

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.vitoraguiardf.bobinabanking.data.entity.Account

@Dao
interface AccountDao: DaoRepository<Account, Int> {
    @Transaction
    @Query("SELECT * FROM Account ORDER BY ID DESC")
    fun findAll(): Array<Account>
    @Transaction
    @Query("SELECT * FROM Account WHERE ID <> :id ORDER BY ID DESC")
    fun findAllExcept(id: Int): Array<Account>
}
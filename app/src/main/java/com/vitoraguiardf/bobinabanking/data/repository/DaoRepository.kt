package com.vitoraguiardf.bobinabanking.data.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface DaoRepository<T, ID> {
    @Update
    fun update(t: T)
    @Insert
    fun insert(t: T)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(t: T)
    @Insert
    fun insertAll(vararg t: T)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(vararg t: T)
    @Delete
    fun delete(t: T)
}
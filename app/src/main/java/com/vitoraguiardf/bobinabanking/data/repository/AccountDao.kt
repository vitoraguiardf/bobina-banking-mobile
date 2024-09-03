package com.vitoraguiardf.bobinabanking.data.repository

import androidx.room.Dao
import com.vitoraguiardf.bobinabanking.data.entity.Account

@Dao
interface AccountDao: DaoRepository<Account, Int>
package com.vitoraguiardf.bobinabanking.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vitoraguiardf.bobinabanking.data.entity.JsonWebToken
import com.vitoraguiardf.bobinabanking.data.entity.Session
import com.vitoraguiardf.bobinabanking.data.entity.TransactionType
import com.vitoraguiardf.bobinabanking.data.entity.User
import com.vitoraguiardf.bobinabanking.data.repository.JsonWebTokenDao
import com.vitoraguiardf.bobinabanking.data.repository.TransactionTypeDao
import com.vitoraguiardf.bobinabanking.data.repository.UserDao

@Database(
    entities = [
        User::class,
        JsonWebToken::class,
        TransactionType::class,
        Session::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun transactionTypeDao(): TransactionTypeDao
    abstract fun jsonWebTokenDao(): JsonWebTokenDao

    companion object{
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): AppDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: baseBuilder(context).also {
                    instance = it
                }
            }
        }
        private fun baseBuilder(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "myDatabase.db"
        ).build()
    }

}
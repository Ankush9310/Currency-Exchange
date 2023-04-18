package com.ac10.currencyexchange.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ac10.currencyexchange.data.dao.CurrencyDao
import com.ac10.currencyexchange.data.dao.ExchangeRateDao
import com.ac10.currencyexchange.data.entities.Currency
import com.ac10.currencyexchange.data.entities.ExchangeRate

@Database(
    entities = [ExchangeRate::class, Currency::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
    abstract fun exchangeRateDao(): ExchangeRateDao

    companion object {
        private const val DB_NAME = "currency_db"

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, DB_NAME
            ).build()
        }

    }



}
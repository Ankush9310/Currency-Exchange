package com.ac10.currencyexchange.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ac10.currencyexchange.data.entities.ExchangeRate

@Dao
interface ExchangeRateDao {

    @Query(" SELECT * FROM exchange_rate order by symbol asc ")
    fun getAll(): List<ExchangeRate>

    @Query(" SELECT * FROM exchange_rate where timestamp > :minTime order by symbol asc ")
    fun getAllGreaterThanTimeStamp(minTime: Long): List<ExchangeRate>

    @Query(" SELECT * FROM exchange_rate WHERE symbol = :value Limit 1")
    fun findBySymbol(value: String): ExchangeRate

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exchangeRate: ExchangeRate)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(currencies: Collection<ExchangeRate>)

    @Delete
    fun delete(exchangeRate: ExchangeRate)


}
package com.ac10.currencyexchange.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ac10.currencyexchange.data.entities.Currency

@Dao
interface CurrencyDao {

    @Query(" SELECT * FROM currency order by symbol asc ")
    fun getAll(): List<Currency>

    @Query(" SELECT * FROM currency WHERE symbol = :value LIMIT 1 ")
    fun findBySymbol(value: String): Currency

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (currency: Currency)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(currencies: Collection<Currency>)

    @Delete
    fun delete(currency: Currency)

}

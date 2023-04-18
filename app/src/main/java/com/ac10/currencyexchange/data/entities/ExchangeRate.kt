package com.ac10.currencyexchange.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rate")
class ExchangeRate (
    @PrimaryKey val symbol: String,
    @ColumnInfo(name = "value") val value: Double,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "base_currency") val baseCurrency: String,
)
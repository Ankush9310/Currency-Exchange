package com.ac10.currencyexchange.data.repositories

import com.ac10.currencyexchange.data.dao.CurrencyDao
import com.ac10.currencyexchange.data.dao.ExchangeRateDao
import com.ac10.currencyexchange.data.entities.Currency
import com.ac10.currencyexchange.data.entities.ExchangeRate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalCurrencyRepository @Inject constructor(
    private val exchangeRateDao: ExchangeRateDao,
    private val currencyDao: CurrencyDao,
) {

    fun getCurrencies(): List<Currency> {
        return currencyDao.getAll()
    }

    fun getRatesWithTimeGreaterThan(minTime: Long): List<ExchangeRate> {
        return exchangeRateDao.getAllGreaterThanTimeStamp(minTime)
    }

    fun saveCurrencies(currencies: List<Currency>) {
        currencyDao.insertAll(currencies)
    }

    fun saveExchangeRates(exchangeRateList: List<ExchangeRate>) {
        exchangeRateDao.insertAll(exchangeRateList)
    }

}
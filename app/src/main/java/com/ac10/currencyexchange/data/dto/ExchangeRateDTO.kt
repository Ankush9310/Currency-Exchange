package com.ac10.currencyexchange.data.dto

data class ExchangeRateDTO (
    val timestamp: Long,
    val base: String,
    val rates: Map<String, Double>
)
package com.ac10.currencyexchange.data.repositories.api

import com.ac10.currencyexchange.data.dto.ExchangeRateDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("currencies.json")
    fun getCurrencies(): Call<Map<String, String>>

    @GET("latest.json")
    fun getLatestExchangeRate(@Query("app_id") appId: String): Call<ExchangeRateDTO>

    companion object {
        private const val BASE_URL = "https://openexchangerates.org/api/"

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)

        }

    }

}
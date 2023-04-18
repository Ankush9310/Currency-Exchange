package com.ac10.currencyexchange.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ac10.currencyexchange.data.CurrencyValue
import com.ac10.currencyexchange.data.entities.Currency
import com.ac10.currencyexchange.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 *  view model class used by [com.ac10.currencyexchange.ui.Fragment]
 */

@HiltViewModel
class CurrencyViewModel @Inject internal constructor(private val useCases: UseCases): ViewModel(){

    // Immutable to observe in the view
    val currencies: LiveData<State<List<Currency>>> get() = _currencies
    private val _currencies = MutableLiveData<State<List<Currency>>>()

    // Immutable to observe int the view
    val currencyValue: LiveData<State<List<CurrencyValue>>> get() = _currenciesValue
    private val _currenciesValue = MutableLiveData<State<List<CurrencyValue>>>()

    // The current select currency
    val selectedCurrency: Currency? get() = _selectedCurrency
    private var _selectedCurrency: Currency? = null

    // the amount to convert in different currencies
    val amountToConvert: Double? get() = _amountToConvert
    private var _amountToConvert: Double? = null

    /**
     *  at startup I'm setting all states to loading and empty in
     *  order to show desired ui widgets and loading all
     *  currencies list (from api or db)
     *
     */

    init {
        loadCurrencies()
    }

    fun loadCurrencies() {
        _currencies.value = State.loading()
        _currenciesValue.value = State.empty()

        viewModelScope.launch {
            val state = withContext(Dispatchers.Default) {
                try {
                    val currenciesValue = useCases.getAllCurrencies()
                    State.success(currenciesValue)
                } catch (exception: Exception) {
                    State.error(exception.message)
                }
            }
            _currencies.value = state

        }
    }

    // this will be invoked when currency is selected from dropdown
    fun changeSelectedCurrency(selectedCurrency: Currency) {
        _selectedCurrency = selectedCurrency
        loadExchangeRates()

    }

    // this will be invoked when amount is changed inside text edit
    fun changeAmount(amount: Double?) {
        amount?.let {
            _amountToConvert = it
            loadExchangeRates()
        }
    }

    /**
     *  load currencies rate, with error handling, I'm using [State] to handle the loading,
     *  error, success states.
     *  error case is handled using custom exceptions. Take a look at [com.ac10.currencyexchange.data.exceptions.NetworkException]
     *  and [com.ac10.currencyexchange.data.exceptions.GenericException]
     */
    private fun loadExchangeRates() {
        if (_selectedCurrency != null && _amountToConvert != null) {
            _currenciesValue.value = State.loading()

            viewModelScope.launch {
                val state = withContext(Dispatchers.Default) {
                    try {
                        val currencyValue = useCases.getRatesByCurrencyAndAmount(
                            _selectedCurrency!!,
                            _amountToConvert!!,
                        )
                        State.success(currencyValue)

                    } catch (exception: Exception) {
                        State.error(exception.message)
                    }
                }
                _currenciesValue.value = state

            }

        }
    }

}










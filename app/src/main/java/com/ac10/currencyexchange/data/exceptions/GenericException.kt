package com.ac10.currencyexchange.data.exceptions

class GenericException: Exception(message) {

    companion object {
        const val message = "An unexpected error has occured, please contact customer support"
    }

}
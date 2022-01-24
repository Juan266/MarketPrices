package com.stock.market.data.remote

import retrofit2.HttpException

class BaseProjectError(exception: Throwable) {
    var code: Int = -1
    var message: String = ""
    //var messageResId: Int = R.string.error_message

    init {
        try {
            if (exception is HttpException) {
                this.code = exception.code()
                this.message = exception.message()
            }
        } catch (exception: Exception) {
            //Do nothing
        }
    }
}
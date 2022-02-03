package com.stock.market.domain.model

import com.stock.market.CONNECTION_TIME_OUT
import com.stock.market.SERVER_ERROR
import com.stock.market.UNAUTHORIZED_ERROR

data class ErrorApp (
    val code: Int,
    val message: String ) {

    fun message(): String {
        when (code) {
            CONNECTION_TIME_OUT -> {
                return "No network connection"
            }
            SERVER_ERROR -> {
                return "Unknown server error"
            }
            UNAUTHORIZED_ERROR -> {
                return "Unauthorized error"
            }
        }
        return message
    }
}

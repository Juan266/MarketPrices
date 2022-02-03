package com.stock.market.domain.model

import com.stock.market.CONNECTION_TIME_OUT
import com.stock.market.DEFAULT_INT_VALUE
import com.stock.market.SERVER_ERROR
import retrofit2.Call
import retrofit2.Response
import com.stock.market.utils.isConnected

abstract class BaseApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}", response.code())
        } catch (e: Exception) {
            if (isConnected()) {
                return error(e.message ?: e.toString(), SERVER_ERROR)
            } else {
                return error(e.message ?: e.toString(), CONNECTION_TIME_OUT)
            }
        }
    }

    private fun <T> error(errorMessage: String, code: Int): NetworkResult<T> =
        NetworkResult.Error(ErrorApp(code, "Api call failed $errorMessage"))

}
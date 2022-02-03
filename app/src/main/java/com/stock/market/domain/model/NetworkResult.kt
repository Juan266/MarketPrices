package com.stock.market.domain.model

sealed class NetworkResult<T>(
    val data: T? = null,
    val error: ErrorApp? = null
    //val code: Int? = null,
    //val message: String? = null,


) {

    class Success<T>(data: T) : NetworkResult<T>(data)

    //class Error<T>(message: String, code: Int, data: T? = null) : NetworkResult<T>(data, code, message)
    class Error<T>(errorApp: ErrorApp, data: T? = null) : NetworkResult<T>(data, errorApp)

    class Loading<T> : NetworkResult<T>()

}
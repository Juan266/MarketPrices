package com.stock.market.data.remote.response

data class TokenResponse(
    var access_token: String? = null,
    var refresh_token: String? = null
)
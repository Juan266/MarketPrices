package com.stock.market.domain.repository

import com.stock.market.data.remote.response.TokenResponse
import io.reactivex.Observable

interface SplashRepository {
    fun getToken(user: String, password: String, grantType: String): Observable<TokenResponse>
    fun refreshToken(token: String, grantType: String): Observable<TokenResponse>
}
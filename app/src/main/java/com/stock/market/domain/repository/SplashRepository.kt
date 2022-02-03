package com.stock.market.domain.repository

import com.stock.market.data.remote.response.TokenResponse
import com.stock.market.domain.model.NetworkResult
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface SplashRepository {
    suspend fun getToken(user: String, password: String, grantType: String): Flow<NetworkResult<TokenResponse>>
    suspend fun refreshToken(token: String, grantType: String): Flow<NetworkResult<TokenResponse>>
}
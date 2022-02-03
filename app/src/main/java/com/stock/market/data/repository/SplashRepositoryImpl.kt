package com.stock.market.data.repository

import com.stock.market.data.remote.response.PanelResponse
import com.stock.market.data.remote.response.TokenResponse
import com.stock.market.data.remote.services.SharesService
import com.stock.market.domain.model.BaseApiResponse
import com.stock.market.domain.model.NetworkResult
import com.stock.market.domain.repository.SplashRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class SplashRepositoryImpl @Inject constructor(private val api: SharesService): SplashRepository,
    BaseApiResponse() {

    override suspend fun getToken(user: String, password: String, grantType: String): Flow<NetworkResult<TokenResponse>> {
        //return api.getToken(user, password, grantType)
        return flow<NetworkResult<TokenResponse>> {
            emit(safeApiCall { api.getToken(user, password, grantType) })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun refreshToken(token: String, grantType: String): Flow<NetworkResult<TokenResponse>> {
        //return api.getTokenRefresh(token, grantType)
        return flow<NetworkResult<TokenResponse>> {
            emit(safeApiCall { api.getTokenRefresh(token, grantType) })
        }.flowOn(Dispatchers.IO)
    }
}
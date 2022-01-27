package com.stock.market.data.repository

import com.stock.market.data.remote.response.TokenResponse
import com.stock.market.data.remote.services.SharesService
import com.stock.market.domain.repository.SplashRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.Observable
import javax.inject.Inject

@ActivityRetainedScoped
class SplashRepositoryImpl @Inject constructor(private val api: SharesService): SplashRepository {

    override fun getToken(user: String, password: String, grantType: String): Observable<TokenResponse> {
        return api.getToken(user, password, grantType)
    }

    override fun refreshToken(token: String, grantType: String): Observable<TokenResponse> {
        return api.getTokenRefresh(token, grantType)
    }
}
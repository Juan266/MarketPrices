package com.stock.market.data.repository

import io.reactivex.Observable
import com.stock.market.data.remote.response.PanelResponse
import com.stock.market.data.remote.services.SharesService
import com.stock.market.domain.model.BaseApiResponse
import com.stock.market.domain.model.NetworkResult
import com.stock.market.domain.repository.PanelRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ActivityRetainedScoped
class PanelRepositoryImpl @Inject constructor(private var api: SharesService) : PanelRepository, BaseApiResponse() {

    override suspend fun getPanel(market: String, country: String): Flow<NetworkResult<PanelResponse>> {
        return flow<NetworkResult<PanelResponse>> {
            emit(safeApiCall { api.getPanel(market, country) })
        }.flowOn(Dispatchers.IO)
    }


}
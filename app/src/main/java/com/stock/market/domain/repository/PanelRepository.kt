package com.stock.market.domain.repository

import com.stock.market.data.remote.response.PanelResponse
import com.stock.market.domain.model.NetworkResult
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface PanelRepository {

    suspend fun getPanel(market: String, country: String): Flow<NetworkResult<PanelResponse>>
}
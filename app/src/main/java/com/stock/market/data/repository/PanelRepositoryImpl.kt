package com.stock.market.data.repository

import io.reactivex.Observable
import com.stock.market.data.remote.response.PanelResponse
import com.stock.market.data.remote.services.SharesService
import com.stock.market.domain.repository.PanelRepository

class PanelRepositoryImpl(private var api: SharesService) : PanelRepository {

    override fun getPanel(market: String, country: String): Observable<PanelResponse> {
        return api.getPanel(market, country)
    }


}
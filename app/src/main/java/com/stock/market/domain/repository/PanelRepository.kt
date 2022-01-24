package com.stock.market.domain.repository

import com.stock.market.data.remote.response.PanelResponse
import io.reactivex.Observable

interface PanelRepository {

    fun getPanel(market: String, country: String): Observable<PanelResponse>
}
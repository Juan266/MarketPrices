package com.stock.market.data.remote.response

import com.squareup.moshi.Json
import com.stock.market.domain.model.Share

class PanelResponse {
    @field:Json(name = "titulos")
    var shares: Array<Share>? = null
}
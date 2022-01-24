package com.stock.market.domain.model

import com.squareup.moshi.Json

class Share {
    @field:Json(name = "simbolo")
    var symbol: String? = null
    @field:Json(name = "ultimoPrecio")
    var lastPrice: Float? = null
    @field:Json(name = "variacionPorcentual")
    var variation: Float? = null
    @field:Json(name = "volumen")
    var totalAmount: Double? = null
    @field:Json(name = "fecha")
    var dateTime: String?= null
}

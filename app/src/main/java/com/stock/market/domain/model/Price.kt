package com.stock.market.domain.model

import android.graphics.Point
import com.squareup.moshi.Json

class Price {
    @field:Json(name= "ultimoPrecio")
    val lastPrice: Double? = null
    @field:Json(name= "apertura")
    var opening: Float? = null
    @field:Json(name= "maximo")
    var max: Float? = null
    @field:Json(name= "minimo")
    var min: Float? = null
    @field:Json(name= "fechaHora")
    var date: String? = null
    @field:Json(name= "cierreAnterior")
    var formerClose: Float? = null
    @field:Json(name= "montoOperado")
    var totalAmount: Double? = null
    @field:Json(name= "precioPromedio")
    var averagePrice: Float? = null
    @field:Json(name= "puntas")
    var points: Array<Point>? = null
    @field:Json(name="variacion")
    var variationRealTime: Float? = null
    var shareSymbol: String? = null
    var diffMinMaxIntra: Float? = null
    var diffMinMax: Float? = null
    var variation: Float? = null
}

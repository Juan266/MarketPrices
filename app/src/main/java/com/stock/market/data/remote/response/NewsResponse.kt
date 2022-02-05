package com.stock.market.data.remote.response

import com.squareup.moshi.Json
import com.stock.market.domain.model.News

class NewsResponse {
    @field:Json(name = "data")
    var news: Array<News>? = null
}
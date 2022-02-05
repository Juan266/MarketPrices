package com.stock.market.domain.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class News (
    val title: String,
    val description: String,
    val keywords: String,
    //@SerializedName("image_url")
    @field:Json(name = "image_url")
    val imageUrl: String )

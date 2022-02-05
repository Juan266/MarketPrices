package com.stock.market.domain.repository

import com.stock.market.data.remote.response.NewsResponse
import com.stock.market.domain.model.NetworkResult
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(language: String): Flow<NetworkResult<NewsResponse>>
}
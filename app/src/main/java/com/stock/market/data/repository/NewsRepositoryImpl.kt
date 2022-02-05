package com.stock.market.data.repository

import com.stock.market.data.remote.response.NewsResponse
import com.stock.market.data.remote.response.PanelResponse
import com.stock.market.data.remote.services.SharesService
import com.stock.market.domain.model.BaseApiResponse
import com.stock.market.domain.model.NetworkResult
import com.stock.market.domain.repository.NewsRepository
import com.stock.market.utils.TOKEN_URL_NEWS
import com.stock.market.utils.URL_NEWS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private var api: SharesService): NewsRepository, BaseApiResponse() {

    override suspend fun getNews(language: String): Flow<NetworkResult<NewsResponse>> {
        return flow<NetworkResult<NewsResponse>> {
            emit(safeApiCall { api.getNews(URL_NEWS, TOKEN_URL_NEWS, language) })
        }.flowOn(Dispatchers.IO)
    }

}
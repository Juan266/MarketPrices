package com.stock.market.ui.news

import com.stock.market.domain.model.News
import com.stock.market.domain.model.Share

interface OnNewsClickListener {
    fun openNews(news: News)
}
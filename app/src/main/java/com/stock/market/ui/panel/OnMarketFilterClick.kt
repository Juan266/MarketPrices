package com.stock.market.ui.panel

import com.stock.market.domain.model.MarketFilter

interface OnMarketFilterClick {
    fun onItemMarketFilterClick(marketFilterItem: MarketFilter)
}
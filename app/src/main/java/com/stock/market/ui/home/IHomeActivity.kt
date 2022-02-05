package com.stock.market.ui.home

import com.stock.market.ui.components.IndicatorView
import com.stock.market.ui.news.NewsViewModel
import com.stock.market.ui.panel.PanelViewModel

interface IHomeActivity {
    fun getPanelViewModel(): PanelViewModel
    fun getNewsViewModel(): NewsViewModel
}
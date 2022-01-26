package com.stock.market.ui.panel

import com.stock.market.domain.model.MarketFilter
import com.stock.market.domain.model.ShareFilter

fun getMarketFilters(): List<MarketFilter> {
    var result = mutableListOf<MarketFilter>()
    result.add(MarketFilter(1, "Merval 25", "Merval 25", "argentina"))
    result.add(MarketFilter(2, "cedears", "Cedears", "argentina"))
    result.add(MarketFilter(3, "SP500", "SyP500", "estados_unidos"))
    result.add(MarketFilter(4, "Nasdaq 100", "Nasdaq 100", "estados_unidos"))
    result.add(MarketFilter(5, "Dow Jones Industrial", "Dow Jones", "estados_unidos"))
    return result
}

fun getShareFilters(): List<ShareFilter> {
    val result = mutableListOf<ShareFilter>()

    val filerBanks = ShareFilter()
    filerBanks.id = 1
    filerBanks.name = "Banks"
    filerBanks.isReset = false
    result.add(filerBanks)

    val filerEnergy = ShareFilter()
    filerEnergy.id = 2
    filerEnergy.name = "Energy"
    filerEnergy.isReset = false
    result.add(filerEnergy)

    val filerIndustry = ShareFilter()
    filerIndustry.id = 3
    filerIndustry.name = "Industry"
    filerIndustry.isReset = false
    result.add(filerIndustry)

    val lowPrices = ShareFilter()
    lowPrices.id = 4
    lowPrices.name = "Low prices"
    lowPrices.isReset = false
    result.add(lowPrices)


    val top4 = ShareFilter()
    top4.id = 5
    top4.name = "Top 4"
    top4.isReset = false
    result.add(top4)

    val reset = ShareFilter()
    reset.id = 6
    reset.name = "Reset"
    reset.isReset = true
    result.add(reset)
    return result
}

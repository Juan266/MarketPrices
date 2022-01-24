package com.stock.market.ui.indicators

import com.stock.market.domain.model.Share

interface OnVariationClickListener {
    fun variationClickTest(share: Share)
}
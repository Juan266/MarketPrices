package com.stock.market.ui.panel

import com.stock.market.domain.model.Share

interface OnPanelClickListener {

    fun openCustomOperation(share: Share)
}
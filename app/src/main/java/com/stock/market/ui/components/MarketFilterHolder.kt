package com.stock.market.ui.components

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stock.market.R
import com.stock.market.domain.model.MarketFilter
import com.stock.market.ui.panel.OnMarketFilterClick
import kotlinx.android.synthetic.main.item_market_filter.view.*

class MarketFilterHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    //private lateinit var marketName: TextView
    lateinit var marketName: TextView


    fun bindItem(marketFilterItem: MarketFilter, listener: OnMarketFilterClick) = with(itemView) {
        marketName = item_market_filter
        marketName.setText(marketFilterItem.name.toString())
        /*itemView.setOnClickListener {
            if (listener != null) {
                selectedItemPosition = position
                listener.onItemMarketFilterClick(marketFilterItem)
            }
        }*/
    }
}
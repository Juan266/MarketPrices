package com.stock.market.ui.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stock.market.R
import com.stock.market.domain.model.MarketFilter
import com.stock.market.ui.panel.OnMarketFilterClick

class MarketFilterAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private lateinit var data: List<MarketFilter>
    private lateinit var listener: OnMarketFilterClick

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MarketFilterHolder) {
            val marketFilterItem = data[position]
            holder.bindItem(marketFilterItem, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MarketFilterHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_market_filter, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (::data.isInitialized) data.size else 0
    }

    fun setData(itemsFilters: List<MarketFilter>) {
        data = itemsFilters
    }

    fun setListener(aListener: OnMarketFilterClick) {
        listener = aListener
    }
}
package com.stock.market.ui.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stock.market.R
import com.stock.market.domain.model.ShareFilter
import com.stock.market.ui.panel.OnShareFilterClick

class ShareFilterChipAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private lateinit var data: List<ShareFilter>
    private lateinit var listener: OnShareFilterClick

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ShareFilterChipHolder) {
            val shareFilterItem = data[position]
            holder.bindItem(shareFilterItem, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShareFilterChipHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_chip_share_filter, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (::data.isInitialized) data.size else 0
    }

    fun setData(itemsFilters: List<ShareFilter>) {
        data = itemsFilters
    }

    fun setListener(aListener: OnShareFilterClick) {
        listener = aListener
    }
}
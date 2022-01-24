package com.stock.market.ui.components

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stock.market.R
import com.stock.market.domain.model.ShareFilter
import com.stock.market.ui.panel.OnShareFilterClick

class ShareFilterChipAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private lateinit var data: List<ShareFilter>
    private lateinit var listener: OnShareFilterClick
    private var selectedShareFilterItemPosition: Int = 6

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        if (holder is ShareFilterChipHolder) {
            val shareFilterItem = data[position]
            holder.bindItem(shareFilterItem, listener)

            holder.itemView.setOnClickListener {
                if (listener != null) {
                    selectedShareFilterItemPosition = position
                    if (shareFilterItem.isReset!!) {
                        listener.onResetShareFilterClick()
                    } else {
                        listener.onItemShareFilterClick(shareFilterItem.id!!)
                    }
                    notifyDataSetChanged()
                }
            }
            if (selectedShareFilterItemPosition == position && !(shareFilterItem.isReset!!)) {
                holder.chip.setColor(R.color.white)
                holder.chip.setTextColor(R.color.black)
            } else if (!(shareFilterItem.isReset!!)) {
                holder.chip.setColor(R.color.my_trade_yellow)
                holder.chip.setTextColor(R.color.black)
            }
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
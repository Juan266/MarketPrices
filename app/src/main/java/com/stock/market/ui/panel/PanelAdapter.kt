package com.stock.market.ui.panel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stock.market.*
import com.stock.market.DEFAULT_INT_VALUE
import com.stock.market.domain.model.Share
import com.stock.market.ui.panel.OnPanelClickListener
import com.stock.market.utils.*

class PanelAdapter constructor(val listener: OnPanelClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var data: List<Share>

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PanelHolder) {
            val share = data[position]
            holder.bindItem(share, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PanelHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_panel_const, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (::data.isInitialized) data.size else 0
    }

    fun updatePanel(shares: List<Share>, filterId: Int) {
        var result = mutableListOf<Share>()
        if (filterId == DEFAULT_INT_VALUE) {
            result = shares.toMutableList()
        } else {
            for (share in shares) {
                if (filterId == SHARE_BANK && isBank(share.symbol!!)) {
                    result.add(share)
                }
                if (filterId == SHARE_ENERGY && isEnergy(share.symbol!!)) {
                    result.add(share)
                }
                if (filterId == SHARE_INDUSTRY && isIndustry(share.symbol!!)) {
                    result.add(share)
                }
                if (filterId == SHARE_LOW_PRICE && isLowPrice(share.symbol!!)) {
                    result.add(share)
                }
                if (filterId == SHARE_TOP4 && isTop4(share.symbol!!)) {
                    result.add(share)
                }
            }
        }
        this.data = result
        notifyDataSetChanged()
    }
}
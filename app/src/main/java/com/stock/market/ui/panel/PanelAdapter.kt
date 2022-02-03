package com.stock.market.ui.panel

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.stock.market.*
import com.stock.market.DEFAULT_INT_VALUE
import com.stock.market.domain.model.Share
import com.stock.market.utils.*

class PanelAdapter constructor(val listener: OnPanelClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private lateinit var data: List<Share>
    private var dataToFilter: List<Share> = mutableListOf()

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
        this.dataToFilter = this.data
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val queryString = constraint?.toString()?.toUpperCase()
                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    dataToFilter
                else
                    dataToFilter.filter {
                        it.symbol?.toUpperCase()!!.contains(queryString)
                    }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                data = if (results?.values == null) mutableListOf() else results.values as List<Share>
                notifyDataSetChanged()
            }
        }
    }
}
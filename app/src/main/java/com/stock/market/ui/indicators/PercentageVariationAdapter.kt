package com.stock.market.indicators

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stock.market.R
import com.stock.market.domain.model.Share
import com.stock.market.ui.indicators.OnVariationClickListener


class PercentageVariationAdapter constructor(val listener: OnVariationClickListener) : RecyclerView.Adapter<RecyclerView.
                    ViewHolder>() {

    private lateinit var data: List<Share>

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PercentageVariationHolder) {
            val share = data[position]
            holder.bindItem(share, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PercentageVariationHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_percentage_variation, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (::data.isInitialized) data.size else 0
    }

    fun updateData(aData: List<Share>) {
        data = aData
        notifyDataSetChanged()

    }

}



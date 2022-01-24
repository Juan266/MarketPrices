package com.stock.market.ui.components

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.stock.market.R
import com.stock.market.domain.model.ShareFilter
import com.stock.market.ui.panel.OnShareFilterClick
import kotlinx.android.synthetic.main.item_chip_share_filter.view.*

class ShareFilterChipHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var chip: ChipView

    fun bindItem(shareFilterItem: ShareFilter, listener: OnShareFilterClick) = with(itemView) {
        chip = item_chip_share_filter_pill
        chip.setText(shareFilterItem.name.toString())

        chip.setColor(if (shareFilterItem.isReset!!) R.color.colorAccent else R.color.my_trade_yellow)
        chip.setTextColor(if (shareFilterItem.isReset!!) R.color.white else R.color.black)
        itemView.setOnClickListener {
            if (listener != null) {
                if (shareFilterItem.isReset!!) {
                    listener.onResetShareFilterClick()
                } else {
                    listener.onItemShareFilterClick(shareFilterItem.id!!)
                }
            }
        }
    }
}
package com.stock.market.indicators

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stock.market.domain.model.Share
import com.stock.market.ui.components.IndicatorView
import com.stock.market.ui.indicators.OnVariationClickListener
import kotlinx.android.synthetic.main.item_percentage_variation.view.*

class PercentageVariationHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var variationItem: IndicatorView

    fun bindItem(shareItem: Share, listener: OnVariationClickListener) = with(itemView) {
        variationItem = item_percentage_variation_square

        variationItem.setShareName(shareItem.symbol!!)
        variationItem.setVariation(shareItem.variation!!)
        variationItem.setImateArrow(shareItem.variation!!)

        itemView.setOnClickListener {
            if (listener != null) {
                listener.variationClickTest(shareItem)
            }
        }
    }
}
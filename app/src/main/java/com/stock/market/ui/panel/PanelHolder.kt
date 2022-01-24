package com.stock.market.ui.panel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stock.market.R
import com.stock.market.domain.model.Share
import com.stock.market.utils.*
import kotlinx.android.synthetic.main.item_panel_const.view.*


class PanelHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var shareName: TextView
    private lateinit var shareLastPrice: TextView
    private lateinit var shareVariation: TextView
    private lateinit var shareDateTime: TextView
    private lateinit var shareClockImage: ImageView
    private lateinit var shareContainer: ConstraintLayout

    fun bindItem(share: Share, listener: OnPanelClickListener) = with(itemView) {
        shareContainer = panel_list_container_const
        shareName = panel_list_share_name_const
        shareLastPrice = panel_list_share_last_price_const
        shareVariation = panel_list_share_variation_const
        shareDateTime = panel_list_share_date_time_const
        shareClockImage = panel_list_share_clock_image_const

        val colorClockImage =  if (isMarketOpen(context))
            ContextCompat.getColor(context, R.color.apple_variation)
                    else ContextCompat.getColor(context, R.color.alizarin_crimson)
        shareClockImage.setColorFilter(colorClockImage)

        shareName.text = share.symbol
        shareLastPrice.text = share.lastPrice.toString()
        shareVariation.text = String.format(context.getString(R.string.panel_holder_share_variation),
            getVariationMoneyText(share.variation!!, share.lastPrice!!),
            getVariationPercentageText(share.variation!!))
        shareDateTime.text = getDate(share.dateTime!! , FORMAT_IOL_API, DATE_PATTERN)
        shareVariation.setTextColor(ContextCompat.getColor(itemView.context, getVariationColor(share.variation!!)))
        shareContainer.setOnClickListener { listener.openCustomOperation(share) }

    }
}
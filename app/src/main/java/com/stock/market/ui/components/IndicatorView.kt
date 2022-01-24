package com.stock.market.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.stock.market.R
import kotlinx.android.synthetic.main.indicator_view.view.*

class IndicatorView : ConstraintLayout {

    private lateinit var indicatorShareNameText: TextView
    private lateinit var indicatorVariationText: TextView
    private lateinit var indicatorImageArrow: ImageView

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        if (!isInEditMode) {
            View.inflate(context, R.layout.indicator_view, this)
            indicatorShareNameText = indicator_share_name
            indicatorVariationText = indicator_variation
            indicatorImageArrow = indicator_image_arrow
        }
    }

    fun setShareName(shareName: String) {
        indicatorShareNameText.text = shareName
    }

    fun setVariation(variationPercentage: Float) {
        indicatorVariationText.text = variationPercentage.toString()
    }

    fun setImateArrow(variationPercentage: Float) {
        if (variationPercentage > 0) {
            indicatorImageArrow.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)
            indicatorImageArrow.setColorFilter(ContextCompat.getColor(context,
                R.color.my_trade_medium_green), android.graphics.PorterDuff.Mode.SRC_IN)
        } else {
            indicatorImageArrow.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
            indicatorImageArrow.setColorFilter(ContextCompat.getColor(context,
                R.color.my_trade_red), android.graphics.PorterDuff.Mode.SRC_IN)
        }
    }
}
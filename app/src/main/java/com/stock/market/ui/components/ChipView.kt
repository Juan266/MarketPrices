package com.stock.market.ui.components

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.stock.market.R
import kotlinx.android.synthetic.main.view_chip.view.*

class ChipView: ConstraintLayout {

    private lateinit var chipText: TextView

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
            View.inflate(context, R.layout.view_chip, this)
            assignResourcesChipView()
        }
    }

    fun setText(text: String) {
        chipText.setText(text)
    }

    fun setColor(color: Int) {
        val background = chipText.background
        if (background is StateListDrawable) {
            background.setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP)
        }
    }

    fun setTextColor(color: Int) {
        chipText.setTextColor(ContextCompat.getColor(context, color))
    }

    private fun assignResourcesChipView() {
        chipText = view_chip_text
    }
}
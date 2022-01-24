package com.stock.market.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stock.market.R
import com.stock.market.domain.model.Share
import com.stock.market.indicators.PercentageVariationAdapter
import com.stock.market.ui.indicators.OnVariationClickListener
import kotlinx.android.synthetic.main.indicator_group_view.view.*

class IndicatorGroupView : ConstraintLayout, OnVariationClickListener {

    private lateinit var indicatorGroupTitle: TextView
    private lateinit var indicatorGroupRecyclerView: RecyclerView
    private var indicatorsAdapter = PercentageVariationAdapter(this)

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
            View.inflate(context, R.layout.indicator_group_view, this)
            indicatorGroupTitle = indicator_group_title
            indicatorGroupRecyclerView = indicator_group_list


            indicatorGroupRecyclerView.adapter = indicatorsAdapter
            val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.shape_indicators_divider)!!)
            indicatorGroupRecyclerView.addItemDecoration(itemDecoration)
            indicatorGroupRecyclerView.setNestedScrollingEnabled(false)

        }
    }

    fun setTitle(title: String) {
        indicatorGroupTitle.text = title
    }

    fun setListItems(listItems: List<Share>) {
        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.setSpanSizeLookup(object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (listItems.size % 2 != 0 && position == listItems.size - 1) {
                    2
                } else 1
            }
        })
        indicatorGroupRecyclerView.setLayoutManager(gridLayoutManager)

        indicatorsAdapter.updateData(listItems)
    }

    //VariationClickListener method
    override fun variationClickTest(share: Share) {
        Toast.makeText(context, "Test Click Variation Share :" + share.symbol +
            " Variation: " + share.variation, Toast.LENGTH_LONG).show()
    }
}
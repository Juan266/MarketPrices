package com.stock.market.ui.components

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stock.market.EMPTY_STRING
import com.stock.market.R
import com.stock.market.domain.model.MarketFilter
import com.stock.market.ui.panel.OnMarketFilterClick
import com.stock.market.ui.panel.getMarketFilters
import kotlinx.android.synthetic.main.view_share_filters.view.*

class MarketFilterView: RelativeLayout, OnMarketFilterClick {

    internal var shareListFilters: RecyclerView? = null
    private var callback: OnMarketFilterClick? = null

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
            RelativeLayout.inflate(context, R.layout.view_share_filters, this)
            assignResourcesShareFilterView()
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            shareListFilters?.setLayoutManager(linearLayoutManager)

            val dividerItemDecoration = DividerItemDecoration(context,LinearLayoutManager.HORIZONTAL)
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider_list)!!)
            shareListFilters?.addItemDecoration(dividerItemDecoration)

            setData()
        }
    }

    private fun assignResourcesShareFilterView() {
        shareListFilters = share_list_filters
    }


    fun setData() {
        val marketAdapter = MarketFilterAdapter()
        marketAdapter.setData(getMarketFilters())
        marketAdapter.setListener(this)
        shareListFilters!!.adapter = marketAdapter

    }

    private fun getTitle(filtersApplied: Int): String {
        return EMPTY_STRING
    }



    override fun onItemMarketFilterClick(filterMarketItem: MarketFilter) {
        callback?.onItemMarketFilterClick(filterMarketItem)
    }

    fun setListener(listener: OnMarketFilterClick) {
        callback = listener
    }
}
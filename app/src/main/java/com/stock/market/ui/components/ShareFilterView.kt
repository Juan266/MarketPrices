package com.stock.market.ui.components

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stock.market.EMPTY_STRING
import com.stock.market.R
import com.stock.market.ui.panel.OnShareFilterClick
import com.stock.market.ui.panel.getShareFilters
import kotlinx.android.synthetic.main.view_share_filters.view.*

class ShareFilterView: RelativeLayout, OnShareFilterClick {

    internal var shareListFilters: RecyclerView? = null
    private var callback: OnShareFilterClick? = null

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
            inflate(context, R.layout.view_share_filters, this)
            assignResourcesShareFilterView()
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            shareListFilters!!.setLayoutManager(linearLayoutManager)
            setData()
        }
    }

    private fun assignResourcesShareFilterView() {
        shareListFilters = share_list_filters
    }


    fun setData() {
        val filterAdapter = ShareFilterChipAdapter()
        filterAdapter.setData(getShareFilters())
        filterAdapter.setListener(this)
        shareListFilters!!.adapter = filterAdapter

    }

    private fun getTitle(filtersApplied: Int): String {
        return EMPTY_STRING
    }


    override fun onItemShareFilterClick(filterItemId: Int) {
        callback!!.onItemShareFilterClick(filterItemId)
    }

    override fun onResetShareFilterClick() {
        callback!!.onResetShareFilterClick()
    }

    fun setListener(listener: OnShareFilterClick) {
        callback = listener
    }
}
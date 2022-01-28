package com.stock.market.ui.indicators

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.stock.market.BaseFragment
import com.stock.market.DEFAULT_INT_VALUE
import com.stock.market.R
import com.stock.market.domain.model.MarketFilter
import com.stock.market.domain.model.Share
import com.stock.market.ui.home.IHomeActivity
import com.stock.market.ui.panel.OnMarketFilterClick
import com.stock.market.ui.panel.PanelViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class IndicatorsFragment : BaseFragment(), OnMarketFilterClick {

    private lateinit var binding: com.stock.market.databinding.FragmentIndicatorsBinding
    private lateinit var listIndicators: List<Share>

    lateinit var callbackHomeActivity: IHomeActivity

    override fun getLayout(): Int {
        return R.layout.fragment_indicators
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.indicatorMarketFilter.setListener(this)

        callbackHomeActivity.getPanelViewModel().result.observe(viewLifecycleOwner, {
            if (it != null) {
                setListIndicators(it.shares!!)
            }
        })
        callbackHomeActivity.getPanelViewModel().error.observe(viewLifecycleOwner, {
            Toast.makeText(context, "Error Indicators" + it.toString(), Toast.LENGTH_SHORT).show()
        })
    }

    private fun setListIndicators(list: Array<Share>) {
        listIndicators = list.asList()
        var sortedListTopGainers = listIndicators.sortedWith(compareByDescending ({it.variation!!}))
        sortedListTopGainers = sortedListTopGainers.slice(1..4)

        var sortedListTopLosers = listIndicators.sortedWith(compareBy ({it.variation!!}))
        sortedListTopLosers = sortedListTopLosers.slice(1..4)

        binding.indicatorGroupPercentageGainers.setTitle("Top Gainers")
        binding.indicatorGroupPercentageGainers.setListItems(sortedListTopGainers)

        binding.indicatorGroupPercentageLosers.setTitle("Top Losers")
        binding.indicatorGroupPercentageLosers.setListItems(sortedListTopLosers)
    }

    override fun onItemMarketFilterClick(marketFilterItem: MarketFilter) {
        callbackHomeActivity.getPanelViewModel().getPanel(marketFilterItem.name, marketFilterItem.country)
    }

    override fun defineAdditionalCallbacks(context: Context) {
        callbackHomeActivity = context as IHomeActivity
    }

}
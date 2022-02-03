package com.stock.market.ui.panel

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.stock.market.*
import com.stock.market.domain.model.MarketFilter
import com.stock.market.domain.model.Share
import com.stock.market.ui.home.HomeActivity
import com.stock.market.ui.home.IHomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

@AndroidEntryPoint
class PanelFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, OnShareFilterClick, OnMarketFilterClick,
    OnPanelClickListener {

    private lateinit var binding: com.stock.market.databinding.FragmentPanelNewBinding
    private val adapterPanel: PanelAdapter = PanelAdapter(this)
    private lateinit var listPanel: List<Share>
    private var filterItemSelected = DEFAULT_INT_VALUE
    private var marketItemSelected = DEFAULT_MARKET_SELECTED
    private var countrySelected = DEFAULT_COUNTRY

    lateinit var callbackHomeActivity: IHomeActivity

    override fun getLayout(): Int {
        return R.layout.fragment_panel_new
    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        binding.panelList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        val dividerItemDecoration = DividerItemDecoration(context,LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider_list)!!)
        binding.panelList.addItemDecoration(dividerItemDecoration)

        binding.panelSwipeRefresh.setOnRefreshListener(this)
        binding.panelFilterViewSectors.setListener(this)
        binding.panelFilterViewMarkets.setListener(this)

        setPanelList()
        return binding.root

    }

    private fun setPanelList() {
        binding.panelList.adapter = adapterPanel
        this.callbackHomeActivity.getPanelViewModel().resultPanel.observe(viewLifecycleOwner, {
            binding.panelSwipeRefresh.isRefreshing = false
            binding.panelProgressBar.visibility = View.GONE
            listPanel = it.shares!!.asList()
            adapterPanel.updatePanel(listPanel, filterItemSelected)
        })
        this.callbackHomeActivity.getPanelViewModel().errorPanel.observe(viewLifecycleOwner, {
            binding.panelSwipeRefresh.isRefreshing = false
            binding.panelProgressBar.visibility = View.GONE
            showErrorSnackBar((activity as HomeActivity).bottomNavigationView, it.message())

        })
    }

    override fun openCustomOperation(share: Share) {
        //goTo(OneOperationCustomActivity.getIntent(requireContext(), share.symbol), false)
    }

    override fun onItemShareFilterClick(filterItemId: Int) {
        filterItemSelected = filterItemId
        callbackHomeActivity.getPanelViewModel().getPanel(marketItemSelected, countrySelected)
    }

    override fun onResetShareFilterClick() {
        filterItemSelected = DEFAULT_INT_VALUE
        callbackHomeActivity.getPanelViewModel().getPanel(marketItemSelected, countrySelected)
    }

    override fun onItemMarketFilterClick(marketFilterItem: MarketFilter) {
        filterItemSelected = DEFAULT_INT_VALUE
        marketItemSelected = marketFilterItem.name
        countrySelected = marketFilterItem.country

        callbackHomeActivity.getPanelViewModel().getPanel(marketItemSelected, countrySelected)
        if (marketItemSelected.toUpperCase(Locale.getDefault()).equals(DEFAULT_MARKET_SELECTED.toUpperCase(
                Locale.getDefault() ))) {
            binding.panelFilterViewSectors.visibility = View.VISIBLE
            binding.panelFilterViewSectors.setData()
        } else {
            binding.panelFilterViewSectors.visibility = View.GONE
        }
    }


    override fun onRefresh() {
        callbackHomeActivity.getPanelViewModel().getPanel(marketItemSelected, countrySelected)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)

        val item = menu.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView

        // search queryTextChange Listener
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                adapterPanel.filter.filter(p0)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                Log.d("onQueryTextChange", "query: " + query)
                adapterPanel.filter.filter(query)
                return false
            }
        })

        //Expand Collapse listener
        item.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                Toast.makeText(context, "Action Collapse", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                Toast.makeText(context, "Action Expand", Toast.LENGTH_LONG).show()
                return true
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)


    }

    override fun defineAdditionalCallbacks(context: Context) {
        callbackHomeActivity = context as IHomeActivity
    }
}
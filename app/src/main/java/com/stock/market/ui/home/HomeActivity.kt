package com.stock.market.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stock.market.BaseActivity
import com.stock.market.R
import com.stock.market.ui.components.IndicatorView
import com.stock.market.ui.indicators.IndicatorsFragment
import com.stock.market.ui.news.NewsFragment
import com.stock.market.ui.news.NewsViewModel
import com.stock.market.ui.panel.PanelFragment
import com.stock.market.ui.panel.PanelViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity: BaseActivity(), IHomeActivity {

    lateinit var bottomNavigationView: BottomNavigationView

    private val viewModelPanel by viewModels<PanelViewModel>()
    private val viewModelNews by viewModels<NewsViewModel>()


    override fun getLayout() = R.layout.activity_home

    override fun getFragment(): Fragment? {
       return null
    }

    override fun initView(savedInstanceState: Bundle?) {
        openFragment(PanelFragment(), false)
        bottomNavigationView = findViewById(R.id.bottom_navigation_view) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.panel -> openFragment(PanelFragment(), false)
                R.id.indicators -> openFragment(IndicatorsFragment(), false)
                R.id.news -> openFragment(NewsFragment(), false)
            }
            true
        }
    }

    override open fun addBackToActionBar() {
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        }
    }

    override fun getPanelViewModel(): PanelViewModel {
        return viewModelPanel
    }

    override fun getNewsViewModel(): NewsViewModel {
        return viewModelNews
    }
}
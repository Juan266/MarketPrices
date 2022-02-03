package com.stock.market.ui.splash

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.stock.market.FullScreenActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : FullScreenActivity(), ISplashActivity {

    private val viewModelSplash by viewModels<SplashViewModel>()

    override fun getFragment(): Fragment? {
        return SplashFragment.newInstance()
    }

    override fun getSplashViewModel(): SplashViewModel {
        return viewModelSplash
    }
}

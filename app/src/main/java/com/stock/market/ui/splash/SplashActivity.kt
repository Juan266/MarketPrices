package com.stock.market.ui.splash

import androidx.fragment.app.Fragment
import com.stock.market.FullScreenActivity

class SplashActivity : FullScreenActivity() {
    override fun getFragment(): Fragment? {
        return SplashFragment.newInstance()
    }
}

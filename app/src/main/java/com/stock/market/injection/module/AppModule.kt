package com.stock.market.injection.module

import com.stock.market.ui.panel.PanelViewModel
import com.stock.market.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val AppModule = module {

    viewModel { SplashViewModel(get()) }
    viewModel { PanelViewModel(get()) }


}
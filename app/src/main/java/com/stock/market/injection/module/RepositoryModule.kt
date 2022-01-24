package com.stock.market.injection.module


import com.stock.market.data.repository.PanelRepositoryImpl
import com.stock.market.data.repository.SplashRepositoryImpl
import com.stock.market.domain.repository.PanelRepository
import com.stock.market.domain.repository.SplashRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single<SplashRepository> { SplashRepositoryImpl(get()) }
    single<PanelRepository> { PanelRepositoryImpl(get()) }

}
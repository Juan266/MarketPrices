package com.stock.market

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.stock.market.injection.module.AppModule
import com.stock.market.injection.module.NetworkModule
import com.stock.market.injection.module.RepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    lateinit var sp: SharedPreferences

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        sp = getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
        initDependencies()

        startKoin {
            androidContext(this@App)
            // use modules
            modules(AppModule, NetworkModule, RepositoryModule)
        }

        instance = this
    }

    private fun initDependencies() {
    }

}
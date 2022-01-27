package com.stock.market

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
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

        /*startKoin {
            androidContext(this@App)
            // use modules
            modules(AppModule, NetworkModule, RepositoryModule)
        }*/

        instance = this
    }

    private fun initDependencies() {
    }

}
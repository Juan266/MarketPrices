package com.stock.market.utils

import android.content.Context
import android.net.ConnectivityManager
import com.stock.market.App



fun isConnected(): Boolean {
    var isConnected = true
    val connectivity = App.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivity != null) {
        val activeNetwork = connectivity.activeNetworkInfo
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
    return isConnected
}
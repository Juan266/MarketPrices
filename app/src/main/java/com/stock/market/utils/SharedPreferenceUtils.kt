package com.stock.market.utils

import android.text.TextUtils
import com.stock.market.App

const val TOKEN = "token"
const val TOKEN_REFRESH = "token_refresh"
const val ENV = "env"

object SharedPreferenceUtils {

    fun setToken(token: String, tokenRefresh: String) {
        val editor = App.instance.sp.edit()
        editor.putString(TOKEN, token)
        editor.putString(TOKEN_REFRESH, tokenRefresh)
        editor.apply()
    }

    fun clearToken() {
        setToken(EMPTY_STRING, EMPTY_STRING)
    }

    fun getTokenLocal(): String? {
            return App.instance.sp.getString(TOKEN, null)
    }

    fun getTokenRefreshLocal(): String? {
        return App.instance.sp.getString(TOKEN_REFRESH, null)
    }

    fun setEnvironment(env: Int) {
        val editor = App.instance.sp.edit()
        editor.putInt(ENV, env)
        editor.apply()
    }

    fun isEnvProd(): Boolean {
        return App.instance.sp.getInt(ENV, 0) == 1
    }

    fun isEmptyToken(): Boolean {
            return TextUtils.isEmpty(App.instance.sp.getString(TOKEN, null)) &&
                    TextUtils.isEmpty(App.instance.sp.getString(TOKEN_REFRESH, null));
        }

}




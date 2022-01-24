package com.stock.market.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.stock.market.BaseViewModel
import com.stock.market.domain.repository.SplashRepository
import com.stock.market.utils.SharedPreferenceUtils
import com.stock.market.utils.getPassword
import com.stock.market.utils.getUserName
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashViewModel(private val splashRepository: SplashRepository): BaseViewModel() {
    val getTokenError: MutableLiveData<Boolean> = MutableLiveData()
    val getTokenSuccess: MutableLiveData<Boolean> = MutableLiveData()

    override fun resolveToken(username: String, password: String) {
        if (SharedPreferenceUtils.isEmptyToken()) {
            getToken(getUserName(), getPassword())
        } else {
            refreshToken()
        }
    }

    @SuppressLint("CheckResult")
    fun getToken(username: String, password: String) {
        splashRepository.getToken(username, password, "password")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {  }
                .subscribe(
                        { result ->
                            val response = result
                            if (response != null) {
                                SharedPreferenceUtils.setToken(response.access_token!!, response.refresh_token!!)
                                onGetTokenSuccess()
                            }
                        },
                        { error ->
                            onGetTokenError(error) }
                )
    }

    @SuppressLint("CheckResult")
    fun refreshToken() {
        splashRepository.refreshToken(SharedPreferenceUtils.getTokenRefreshLocal()!!,
                "refresh_token")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {  }
                .subscribe(
                        { result ->
                            val response = result
                            if (response != null) {
                                SharedPreferenceUtils.setToken(response.access_token!!, response.refresh_token!!)
                                onGetTokenSuccess()
                            }
                        },
                        { error -> onGetTokenError(error) }
                )
    }

    private fun onGetTokenSuccess() {
        getTokenSuccess.value = true
    }

    private fun onGetTokenError(error: Throwable) {
        SharedPreferenceUtils.clearToken()
        resolveToken(getUserName(), getPassword())
        getTokenError.value = true
        ///processError(error)
    }
}
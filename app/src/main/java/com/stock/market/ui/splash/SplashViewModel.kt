package com.stock.market.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stock.market.BaseViewModel
import com.stock.market.CONNECTION_TIME_OUT
import com.stock.market.DEFAULT_INT_VALUE
import com.stock.market.SERVER_ERROR
import com.stock.market.data.remote.response.TokenResponse
import com.stock.market.domain.model.ErrorApp
import com.stock.market.domain.model.NetworkResult
import com.stock.market.domain.repository.SplashRepository
import com.stock.market.utils.SharedPreferenceUtils
import com.stock.market.utils.getPassword
import com.stock.market.utils.getUserName
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@HiltViewModel
class SplashViewModel @Inject constructor(private val splashRepository: SplashRepository): BaseViewModel() {
    val _resultToken : MutableLiveData<Boolean> = MutableLiveData()
    val resultToken: LiveData<Boolean> = _resultToken
    val _errorToken: MutableLiveData<ErrorApp> = MutableLiveData()
    val errorToken: LiveData<ErrorApp> = _errorToken

    override fun resolveToken(username: String, password: String) {
        if (SharedPreferenceUtils.isEmptyToken()) {
            getToken(getUserName(), getPassword())
        } else {
            refreshToken()
        }
    }

    fun getToken(username: String, password: String)  = viewModelScope.launch {
        splashRepository.getToken(username, password, "password").collect { values ->
            when (values) {
                is NetworkResult.Success -> {
                    onGetTokenSuccess(values.data!!)
                }
                is NetworkResult.Error ->  {
                    onGetTokenError(values.error!!)
                }
            }
        }
    }

    fun refreshToken() = viewModelScope.launch {
        splashRepository.refreshToken(SharedPreferenceUtils.getTokenRefreshLocal()!!,
            "refresh_token").collect { values ->
            when (values)  {
                is NetworkResult.Success -> {
                    onGetTokenSuccess(values.data!!)
                }
                is NetworkResult.Error -> {
                    onGetTokenError(values.error!!)
                }
            }
        }
    }

    private fun onGetTokenSuccess(tokenResponse: TokenResponse) {
        SharedPreferenceUtils.setToken(tokenResponse.access_token!!, tokenResponse.refresh_token!!)
        _resultToken.value = true
    }

    private fun onGetTokenError(errorApp: ErrorApp) {
       SharedPreferenceUtils.clearToken()
        if (!(errorApp.code == CONNECTION_TIME_OUT) && !(errorApp.code == SERVER_ERROR)) {
            resolveToken(getUserName(), getPassword())
        }
       _errorToken.value = errorApp
    }
}
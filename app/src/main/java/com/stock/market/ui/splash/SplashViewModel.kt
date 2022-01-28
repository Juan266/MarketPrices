package com.stock.market.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stock.market.BaseViewModel
import com.stock.market.data.remote.response.TokenResponse
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
    val _errorToken: MutableLiveData<String> = MutableLiveData()
    val errorToken: LiveData<String> = _errorToken

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
                    onGetTokenError(values.message!!)
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
                    onGetTokenError(values.message!!)
                }
            }
        }
    }

    private fun onGetTokenSuccess(tokenResponse: TokenResponse) {
        SharedPreferenceUtils.setToken(tokenResponse.access_token!!, tokenResponse.refresh_token!!)
        _resultToken.value = true
    }

    private fun onGetTokenError(error: String) {
       SharedPreferenceUtils.clearToken()
       resolveToken(getUserName(), getPassword())
       _errorToken.value = error
    }
}
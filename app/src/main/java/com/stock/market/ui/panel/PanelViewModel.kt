package com.stock.market.ui.panel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stock.market.BaseViewModel
import com.stock.market.DEFAULT_COUNTRY
import com.stock.market.DEFAULT_MARKET_SELECTED
import com.stock.market.data.remote.response.PanelResponse
import com.stock.market.domain.model.NetworkResult
import com.stock.market.domain.model.Share
import com.stock.market.domain.repository.PanelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect


@HiltViewModel
class PanelViewModel @Inject constructor(private val panelRepositoty: PanelRepository): BaseViewModel() {
    val _result: MutableLiveData<PanelResponse> = MutableLiveData()
    val result: LiveData<PanelResponse> = _result
    val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    lateinit var shares: Array<Share>

    init {
        getPanel(DEFAULT_MARKET_SELECTED, DEFAULT_COUNTRY)
    }

    fun getPanel(market: String, country: String) = viewModelScope.launch {
        panelRepositoty.getPanel(market, country).collect { values ->
            when (values) {
                is NetworkResult.Success -> {
                    _result.value = values.data
                }
                is NetworkResult.Error ->  {
                    _error.value = values.message
                }
            }
        }
    }

    /*private fun onGetPanelSuccess(shares: Array<Share>, filterId: Int) {
        if (filterId == DEFAULT_INT_VALUE) {
            setShares(shares)
        }
        panelListData.value = shares
        filterIdData.value = filterId
        refreshing.value = false
        showProgress.value = false
    }*/

   /*private fun onGetPanelError(error: Throwable) {
        refreshing.value = false
        showProgress.value = false
        processError(error)
    }*/
}
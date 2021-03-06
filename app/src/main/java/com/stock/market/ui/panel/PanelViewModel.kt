package com.stock.market.ui.panel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stock.market.BaseViewModel
import com.stock.market.DEFAULT_COUNTRY
import com.stock.market.DEFAULT_MARKET_SELECTED
import com.stock.market.data.remote.response.PanelResponse
import com.stock.market.domain.model.ErrorApp
import com.stock.market.domain.model.NetworkResult
import com.stock.market.domain.model.Share
import com.stock.market.domain.repository.PanelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect


@HiltViewModel
class PanelViewModel @Inject constructor(private val panelRepositoty: PanelRepository): BaseViewModel() {
    val _resultPanel: MutableLiveData<PanelResponse> = MutableLiveData()
    val resultPanel: LiveData<PanelResponse> = _resultPanel
    val _errorPanel: MutableLiveData<ErrorApp> = MutableLiveData()
    val errorPanel: LiveData<ErrorApp> = _errorPanel

    lateinit var shares: Array<Share>

    init {
        getPanel(DEFAULT_MARKET_SELECTED, DEFAULT_COUNTRY)
    }

    fun getPanel(market: String, country: String) = viewModelScope.launch {
        panelRepositoty.getPanel(market, country).collect { values ->
            when (values) {
                is NetworkResult.Success -> {
                    _resultPanel.value = values.data
                }
                is NetworkResult.Error ->  {
                    _errorPanel.value = values.error
                }
            }
        }
    }
}
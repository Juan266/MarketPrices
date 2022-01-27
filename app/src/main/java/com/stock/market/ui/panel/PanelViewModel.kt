package com.stock.market.ui.panel

import androidx.lifecycle.MutableLiveData
import com.stock.market.BaseViewModel
import com.stock.market.DEFAULT_COUNTRY
import com.stock.market.DEFAULT_INT_VALUE
import com.stock.market.DEFAULT_MARKET_SELECTED
import com.stock.market.domain.model.Share
import com.stock.market.domain.repository.PanelRepository
import com.stock.market.utils.setShares
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PanelViewModel @Inject constructor(private val panelRepositoty: PanelRepository): BaseViewModel() {

    var panelListData: MutableLiveData<Array<Share>> = MutableLiveData()
    var filterIdData: MutableLiveData<Int> = MutableLiveData()
    val refreshing: MutableLiveData<Boolean> = MutableLiveData()
    val showProgress: MutableLiveData<Boolean> = MutableLiveData()

    lateinit var shares: Array<Share>

    init {
        getPanel(DEFAULT_INT_VALUE, DEFAULT_MARKET_SELECTED, DEFAULT_COUNTRY)
        refreshing.value = false
        showProgress.value = true

    }

    fun getPanel(filterId: Int, market: String, country: String) {

        //addDisposable(api.getPanel()
        addDisposable(panelRepositoty.getPanel(market, country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    refreshing.value = true
                }
                .subscribe(
                        { response ->
                            if (response != null) {
                                onGetPanelSucccess(response.shares!!, filterId)
                            }
                        },
                        { error -> onGetPanelError(error) }
                )
        )
    }

    private fun onGetPanelSucccess(shares: Array<Share>, filterId: Int) {
        if (filterId == DEFAULT_INT_VALUE) {
            setShares(shares)
        }
        panelListData.value = shares
        filterIdData.value = filterId
        refreshing.value = false
        showProgress.value = false
    }

    private fun onGetPanelError(error: Throwable) {
        refreshing.value = false
        showProgress.value = false
        processError(error)
    }
}
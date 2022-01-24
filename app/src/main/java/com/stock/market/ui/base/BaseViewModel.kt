package com.stock.market

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import com.stock.market.data.remote.BaseProjectError
import com.stock.market.utils.getPassword
import com.stock.market.utils.getUserName

abstract class BaseViewModel() : ViewModel() {
    val generalErrorMessage: MutableLiveData<String> = MutableLiveData()
    val generalErrorMessageResId: MutableLiveData<Int> = MutableLiveData()
    val progressVisibility: MutableLiveData<Boolean> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    init {
    }

    open fun resolveToken(username: String, password: String) {
        //DO NOTHING
    }


    protected fun processError(error: Throwable) {
        val baseProjectError = BaseProjectError(error)
        if (baseProjectError.code == 401) {
            resolveToken(getUserName(), getPassword())
        }
        if (TextUtils.isEmpty(baseProjectError.message))
            //generalErrorMessageResId.value = baseProjectError.messageResId
        else
            generalErrorMessage.value = baseProjectError.message
    }



    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun clearDisposables() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposables()
    }
}
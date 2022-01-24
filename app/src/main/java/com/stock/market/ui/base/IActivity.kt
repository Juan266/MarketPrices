package com.stock.market

interface IActivity {
    fun showError(errorMessage: String)
    fun showError(errorMessageResId: Int)
    fun showSuccess(messageResId: Int)
    fun hideKeyboard()
}
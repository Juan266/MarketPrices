package com.stock.market

import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager


abstract class BaseActivity : AppCompatActivity(), IActivity {
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())

        if (savedInstanceState == null) {
            initView(savedInstanceState)
        }
        addBackToActionBar()
    }

    protected open fun addBackToActionBar() {
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    protected open fun initView(savedInstanceState: Bundle?) {
        val fragment = getFragment()
        if (fragment != null) {
            openFragment(fragment, false)
        }
    }

    protected fun openFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(getContainerResId(), fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
    }

    protected open fun getLayout() = R.layout.activity_full_screen

    protected open fun getContainerResId() = R.id.container

    protected abstract fun getFragment(): Fragment?

    override fun showError(errorMessage: String) {
        errorSnackbar = Snackbar.make(this.findViewById(getContainerResId()), errorMessage, Snackbar.LENGTH_LONG)
        setSnackbarColor(R.color.alizarin_crimson)
        errorSnackbar?.show()
    }

    private fun setSnackbarColor(color: Int) {
        errorSnackbar?.view?.setBackgroundColor(ContextCompat.getColor(baseContext, color))
    }

    override fun showError(errorMessageResId: Int) {
        errorSnackbar = Snackbar.make(this.findViewById(getContainerResId()), errorMessageResId, Snackbar.LENGTH_LONG)
        setSnackbarColor(R.color.alizarin_crimson)
        errorSnackbar?.show()
    }

    override fun showSuccess(messageResId: Int) {
        errorSnackbar = Snackbar.make(this.findViewById(getContainerResId()), messageResId, Snackbar.LENGTH_LONG)
        setSnackbarColor(R.color.apple_variation)
        errorSnackbar?.show()
    }

    override fun hideKeyboard() {
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val onOptionsItemSelected = super.onOptionsItemSelected(item)
        when (item!!.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return onOptionsItemSelected
    }

    protected fun getFragmentOnContainer(): Fragment? {
        return supportFragmentManager.findFragmentById(getContainerResId())
    }
}
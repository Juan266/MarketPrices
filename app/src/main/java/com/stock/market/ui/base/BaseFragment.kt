package com.stock.market

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import android.view.*


abstract class BaseFragment : Fragment() {
    lateinit var callback: IActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(getLayout(), container, false)
    }

    override fun onResume() {
        super.onResume()
        ///Tracking.getInstance().onShowScreen(getViewName(), getViewProperties())
    }

    protected abstract fun getLayout(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as IActivity
    }

    protected fun goTo(classToGo: Class<*>, finishActivity: Boolean) {
        if (activity != null && isAdded) {
            goToIntent(Intent(context, classToGo), finishActivity, false)
        }
    }

    fun goTo(intent: Intent, finishActivity: Boolean) {
        if (activity != null && isAdded) {
            goToIntent(intent, finishActivity, false)
        }
    }

    private fun goToIntent(intent: Intent, finishActivity: Boolean, slideAnimation: Boolean) {
        startActivity(intent)
        /*if (slideAnimation)
            activitySlideIn(activity)
        else
            activityFadeInOut(activity)*/
        if (finishActivity) {
            requireActivity().finish()
        }
    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        if (hasMenu()) {
            inflater.inflate(getMenuResId(), menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }*/

    open fun getMenuResId(): Int {
        return DEFAULT_INT_VALUE
    }

    private fun hasMenu(): Boolean {
        return getMenuResId() != DEFAULT_INT_VALUE
    }

    fun showError(textInputLayout: TextInputLayout, value: Int) {
        textInputLayout.error = requireContext().getString(value)
    }

    protected open fun getViewName(): String? {
        return null
    }

    /*protected open fun getViewProperties(): JsonObject? {
        return null
    }*/

    protected fun showSnackBarColor(snackbar: Snackbar, color: Int) {
        if (activity != null) {
            snackbar.view.setBackgroundColor(ContextCompat.getColor(requireActivity(), color))
            snackbar.show()
        }
    }

    protected fun showSuccessSnackBar(containerView: View, message: String) {
        showSnackBarColor(Snackbar.make(containerView, message, Snackbar.LENGTH_LONG),
            R.color.mountain_meadow)
    }

    protected fun showErrorSnackBar(containerView: View, message: String) {
        val snackbar = Snackbar.make(containerView, message, Snackbar.LENGTH_LONG)
        showSnackBarColor(snackbar, R.color.alizarin_crimson)
    }
}
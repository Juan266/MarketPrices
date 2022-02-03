package com.stock.market

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import android.view.*
import com.stock.market.ui.home.HomeActivity


abstract class BaseFragment : Fragment() {
    lateinit var callback: IActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    protected abstract fun getLayout(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as IActivity
        defineAdditionalCallbacks(context)
    }

    protected abstract fun defineAdditionalCallbacks(context: Context)

    protected fun goTo(classToGo: Class<*>, finishActivity: Boolean) {
        if (activity != null && isAdded) {
            goToIntent(Intent(context, classToGo), finishActivity, false)
        }
    }

    /*fun goTo(intent: Intent, finishActivity: Boolean) {
        if (activity != null && isAdded) {
            goToIntent(intent, finishActivity, false)
        }
    }*/

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

    /*open fun getMenuResId(): Int {
        return DEFAULT_INT_VALUE
    }*/

    /*private fun hasMenu(): Boolean {
        return getMenuResId() != DEFAULT_INT_VALUE
    }*/

    protected fun showSnackBarColor(snackbar: Snackbar, color: Int, anchorView: View) {
        if (activity != null) {
            snackbar.let {
                if (activity is HomeActivity) {
                    it.anchorView = anchorView
                }
                it.view.setBackgroundColor(ContextCompat.getColor(requireActivity(), color))
                it.show()
            }
        }
    }

    protected fun showSuccessSnackBar(containerView: View, message: String) {
        val snackbar = Snackbar.make(containerView, message, Snackbar.LENGTH_LONG)
        showSnackBarColor(snackbar, R.color.mountain_meadow, containerView)
    }

    protected fun showErrorSnackBar(containerView: View, message: String) {
        val snackbar = Snackbar.make(containerView, message, Snackbar.LENGTH_LONG)
        showSnackBarColor(snackbar, R.color.alizarin_crimson, containerView)
    }
}
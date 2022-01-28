package com.stock.market.ui.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.stock.market.BaseFragment
import com.stock.market.R
import com.stock.market.ui.home.HomeActivity
import com.stock.market.utils.getPassword
import com.stock.market.utils.getUserName
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private lateinit var binding: com.stock.market.databinding.FragmentSplashBinding
    lateinit var callbackSplashActivity: ISplashActivity

    companion object {
        fun newInstance() = SplashFragment()
    }

    override fun getLayout(): Int {
        return R.layout.fragment_splash
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)

        callbackSplashActivity.getSplashViewModel().resolveToken(getUserName(), getPassword())

        callbackSplashActivity.getSplashViewModel().resultToken.observe(viewLifecycleOwner, Observer { success ->
            if (success != null) {
                //TODO: move to result code
                goTo(HomeActivity::class.java, true)
            }
        })

        callbackSplashActivity.getSplashViewModel().errorToken.observe(viewLifecycleOwner,  Observer {
            Toast.makeText(requireContext(), "Getting token error", Toast.LENGTH_LONG).show()
        })
        return binding.root
    }

    override fun defineAdditionalCallbacks(context: Context) {
        callbackSplashActivity = context as ISplashActivity
    }


}
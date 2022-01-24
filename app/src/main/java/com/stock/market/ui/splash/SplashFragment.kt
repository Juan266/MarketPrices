package com.stock.market.ui.splash

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
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashFragment : BaseFragment() {

    private val modelSplash: SplashViewModel by viewModel()
    private lateinit var binding: com.stock.market.databinding.FragmentSplashBinding


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

        modelSplash.resolveToken(getUserName(), getPassword())

        modelSplash.getTokenSuccess.observe(viewLifecycleOwner, Observer { success ->
            if (success != null) {
                //TODO: move to result code
                goTo(HomeActivity::class.java, true)
            }
        })

        modelSplash.getTokenError.observe(viewLifecycleOwner,  Observer {
            Toast.makeText(requireContext(), "Getting token error", Toast.LENGTH_LONG)
            //goTo(HomeActivity::class.java, true)
        })
        return binding.root
    }
}
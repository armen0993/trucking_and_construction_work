package com.ml.truckingandconstructionwork.presentation.ui.splash

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.FragmentSplashBinding

import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.utils.Constants.SPLASH_TYPE
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment() : BaseFragment<BaseViewModel,FragmentSplashBinding>() {

    override val binding: FragmentSplashBinding by viewBinding()
    override val viewModel: SplashViewModel by viewModel()
    private lateinit var sharedPref: SharedPreferences
    private var userId=""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        lifecycleScope.launch {


            if (sharedPref.getBoolean("skiped",false)){
                viewModel.getUserDetailWithSharedPref()
                delay(3000)
                navigateFragment(SplashFragmentDirections.actionSplashFragmentToMainFragment().setUserId(userId))
            }else{
                delay(3000)
                navigateFragment(SplashFragmentDirections.actionSplashFragmentToSignInFragment().setSignInType(SPLASH_TYPE))
            }

        }
    }

    override fun onEach() {
        onEach(viewModel.userId){
            userId = it

        }
    }
}
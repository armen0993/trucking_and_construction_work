package com.ml.truckingandconstructionwork.presentation.ui.splash

import androidx.lifecycle.lifecycleScope
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

    private var userId=""

    override fun onView() {
        viewModel.getSkippedTypeWithSharedPref()
    }

    override fun onEach() {
        onEach(viewModel.userId){
            userId = it
        }
        onEach(viewModel.skipped){
            skippedType(it)
        }
    }

    private fun skippedType(skipped:Boolean){
        lifecycleScope.launch {
            if (skipped){
                viewModel.getUserDetailWithSharedPref()
                delay(3000)
                navigateFragment(SplashFragmentDirections.actionSplashFragmentToMainFragment().setUserId(userId))
            }else{
                delay(3000)
                navigateFragment(SplashFragmentDirections.actionSplashFragmentToSignInFragment().setSignInType(SPLASH_TYPE))
            }
        }
    }
}
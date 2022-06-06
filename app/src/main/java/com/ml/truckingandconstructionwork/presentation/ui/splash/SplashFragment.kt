package com.ml.truckingandconstructionwork.presentation.ui.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.FragmentSplashBinding

import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            delay(3000)
            navigateFragment(R.id.action_splashFragment_to_mainFragment)
        }
    }
}
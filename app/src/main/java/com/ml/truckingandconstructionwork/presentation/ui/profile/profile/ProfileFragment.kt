package com.ml.truckingandconstructionwork.presentation.ui.profile.profile

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment() : BaseFragment<BaseViewModel, FragmentProfileBinding>() {

    override val binding: FragmentProfileBinding by viewBinding()
    override val viewModel: ProfileViewModel by viewModel()





}
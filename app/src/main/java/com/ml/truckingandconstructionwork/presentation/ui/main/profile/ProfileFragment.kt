package com.ml.truckingandconstructionwork.presentation.ui.main.profile

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.FragmentForgotPasswordBinding
import com.ml.truckingandconstructionwork.databinding.FragmentProfileBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment() : BaseFragment<BaseViewModel,FragmentProfileBinding>() {

    override val binding: FragmentProfileBinding by viewBinding()
    override val viewModel: ProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.enableLeftItem(true)

        openDrawer()
    }

    private fun openDrawer() {
        val drawer = activity?.findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        binding.toolbar.setOnLeftClickListener {
            drawer?.openDrawer(Gravity.LEFT)

        }
    }

}
package com.ml.truckingandconstructionwork.presentation.ui.main

import android.view.Gravity
import androidx.drawerlayout.widget.DrawerLayout
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.FragmentMainBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<MainViewModel,FragmentMainBinding>() {
    override val viewModel: MainViewModel by viewModel()
    override val binding: FragmentMainBinding by viewBinding()

    override fun onView() {
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
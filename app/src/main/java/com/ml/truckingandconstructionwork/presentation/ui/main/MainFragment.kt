package com.ml.truckingandconstructionwork.presentation.ui.main

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.FragmentMainBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    var emailNotEmpty = false
    var passwordNotEmpty = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.enableLeftItem(true)

        openDrawer()
        validation()
        if (emailNotEmpty && passwordNotEmpty)binding.btnSignIn.isEnabled = true
    }

    private fun validation() {

        binding.email.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
               emailNotEmpty = true
            }
        }
        binding.password.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
               passwordNotEmpty = true
            }
        }

    }

    private fun openDrawer() {
        val drawer = activity?.findViewById<DrawerLayout>(R.id.drawer_layout)
        binding.toolbar.setOnLeftClickListener {
            drawer?.openDrawer(Gravity.LEFT)
        }
    }
}
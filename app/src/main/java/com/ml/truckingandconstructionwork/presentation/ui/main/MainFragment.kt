package com.ml.truckingandconstructionwork.presentation.ui.main

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.navArgs
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogAddJobBinding
import com.ml.truckingandconstructionwork.databinding.FragmentMainBinding
import com.ml.truckingandconstructionwork.databinding.MainHeaderBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {
    override val viewModel: MainViewModel by viewModel()
    override val binding: FragmentMainBinding by viewBinding()
    private val args: MainFragmentArgs by navArgs()
    private lateinit var drawerLayout: DrawerLayout
    private val bindingDrawer by lazy { MainHeaderBinding.inflate(layoutInflater) }
    var name = ""


    override fun onView() {
        drawerLayout = activity?.findViewById<DrawerLayout>(R.id.drawer_layout)!!
        binding.toolbar.enableLeftItem(true)
        val userId = arguments?.getString("userid")
        if (userId != null) {
            viewModel.getUserDetails(userId)
        }
        openDrawer()
    }

    override fun onEach() {
        onEach(viewModel.userDetails) {
            name = "${it.name} ${it.surname}"
        }
        onEach(viewModel.showProgressBar) {
            showProgress(it)
        }
    }

    override fun onViewClick() {
        with(binding){
            sections.setOnClickListener {
                navigateFragment(MainFragmentDirections.actionMainFragmentToSectionsFragment())
            }
            allSections.setOnClickListener {
                navigateFragment(MainFragmentDirections.actionMainFragmentToSpecialEquipmentsListFragment())
            }
        }
    }

    private fun showProgress(show: EmptyView.State) {
        when (show) {
            EmptyView.State.LOADING -> binding.emptyView.showLoader()
            else -> binding.emptyView.hide()
        }

    }

    private fun openDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        binding.toolbar.setOnLeftClickListener {
            drawerLayout.openDrawer(Gravity.LEFT)
            bindingDrawer.drawerFullName.text = name

        }
    }
}
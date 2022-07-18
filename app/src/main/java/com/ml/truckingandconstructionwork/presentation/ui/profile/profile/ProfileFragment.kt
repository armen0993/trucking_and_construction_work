package com.ml.truckingandconstructionwork.presentation.ui.profile.profile

import android.os.Bundle
import android.view.View.VISIBLE
import androidx.navigation.fragment.navArgs
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import com.ml.truckingandconstructionwork.databinding.FragmentProfileBinding
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<BaseViewModel, FragmentProfileBinding>() {

    override val binding: FragmentProfileBinding by viewBinding()
    override val viewModel: ProfileViewModel by viewModel()
    private val args:ProfileFragmentArgs by navArgs()
    override fun onView() {

        val userId =arguments?.getString("userid")

        if (userId != null) {
            viewModel.getUserDetails(userId)
        }
    }


    override fun onEach() {
        onEach(viewModel.userDetails){
            binding.itemProfileName.editProfile.text = "${it.name} ${it.surname}"
        }
        onEach(viewModel.showProgressBar){
            showProgress(it)
        }
    }

    private fun showProgress(show: EmptyView.State) {
        binding.emptyView.visibility = VISIBLE
        when(show){
            EmptyView.State.LOADING-> binding.emptyView.showLoader()
            else -> binding.emptyView.hide()
        }

    }





}
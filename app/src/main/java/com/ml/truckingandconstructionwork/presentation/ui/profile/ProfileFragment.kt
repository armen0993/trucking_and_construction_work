package com.ml.truckingandconstructionwork.presentation.ui.profile

import android.view.View.VISIBLE
import androidx.navigation.fragment.navArgs
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.base.BaseViewModel
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import com.ml.truckingandconstructionwork.databinding.FragmentProfileBinding
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView
import com.ml.truckingandconstructionwork.presentation.utils.Constants.USER_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<BaseViewModel, FragmentProfileBinding>() {

    override val binding: FragmentProfileBinding by viewBinding()
    override val viewModel: ProfileViewModel by viewModel()
    override fun onView() {
        getUser()

    }

    private fun getUser() {
        val userId = arguments?.getString(USER_ID)
        if (userId != null) {
            viewModel.getUserDetails(userId)
        }
    }


    override fun onEach() {
        onEach(viewModel.userDetails) {
            binding.itemProfileName.editProfile.text = "${it.name} ${it.surname}"
        }
        onEach(viewModel.showProgressBar) {
            showProgress(it)
        }
    }

    private fun showProgress(show: EmptyView.State) {
        binding.emptyView.visibility = VISIBLE
        when (show) {
            EmptyView.State.LOADING -> binding.emptyView.showLoader()
            else -> binding.emptyView.hide()
        }

    }


}
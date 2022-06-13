package com.ml.truckingandconstructionwork.presentation.ui.main.registration

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.AlertDialogEnterPinBinding
import com.ml.truckingandconstructionwork.databinding.FragmentPersonalDetailsBinding
import com.ml.truckingandconstructionwork.presentation.Constants.CLIENT
import com.ml.truckingandconstructionwork.presentation.Constants.DRIVER
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment


class PersonalDetailsFragment : BaseFragment<FragmentPersonalDetailsBinding>(
    FragmentPersonalDetailsBinding::inflate
) {
    private val args: PersonalDetailsFragmentArgs by navArgs()
    private val bindingAlertDialog by lazy { AlertDialogEnterPinBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.enableLeftItem(true)

        //Temp
        binding.btnNext.isEnabled = true

//        checkerType(args.userType)

        onClick()
    }

    private fun onClick() {
        binding.btnNext.setOnClickListener {
            startAlertDialog()

        }
    }

//    private fun checkerType(type:String){
//        when (type) {
//            CLIENT -> {
//                binding.typeUser.text = CLIENT
//            }
//            DRIVER ->
//                binding.typeUser.text = DRIVER
//
//        }
//    }

    private fun startAlertDialog() {
        if (bindingAlertDialog.root.parent != null) (bindingAlertDialog.root.parent as ViewGroup).removeView(
            bindingAlertDialog.root
        )
        val dialog = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.ResetTheme
        )
            .setCancelable(false)
            .setView(bindingAlertDialog.root)
            .show()

        bindingAlertDialog.dialogAlertConfirmButton.setOnClickListener {
            dialog.dismiss()
            navigateFragment(PersonalDetailsFragmentDirections.actionPersonalDetailsFragmentToCreateLoginPasswordFragment())
        }
        bindingAlertDialog.dialogAlertCancelButton.setOnClickListener {
            dialog.dismiss()
        }
    }

}
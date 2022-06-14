package com.ml.truckingandconstructionwork.presentation.ui.main.registration

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.FragmentCreatePinBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.models.UserDetails

class CreatePinFragment : BaseFragment<FragmentCreatePinBinding>(FragmentCreatePinBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick(){
        with(binding){
            doNotCreate.setOnClickListener {
                navigateFragment(R.id.action_createPinFragment_to_createFingerprintFragment)
            }
            fingerprintImage.setOnClickListener {
                navigateFragment(R.id.action_createPinFragment_to_createFingerprintFragment)
            }
        }
    }


}
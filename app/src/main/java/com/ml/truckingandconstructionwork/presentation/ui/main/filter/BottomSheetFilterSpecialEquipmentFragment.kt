package com.ml.truckingandconstructionwork.presentation.ui.main.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ml.truckingandconstructionwork.R

class BottomSheetFilterSpecialEquipmentFragment : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(
            R.layout.fragment_bottom_sheet_filter_special_equipment,
            container,
            false
        )
    }


}
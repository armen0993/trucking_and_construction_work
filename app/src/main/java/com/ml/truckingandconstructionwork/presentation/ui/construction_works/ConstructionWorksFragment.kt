package com.ml.truckingandconstructionwork.presentation.ui.construction_works

import android.os.Bundle
import android.view.View
import com.ml.truckingandconstructionwork.databinding.FragmentConstructionWorksBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.custom_view.EmptyView


class ConstructionWorksFragment : BaseFragment<FragmentConstructionWorksBinding>(FragmentConstructionWorksBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding.emptyView.showEmpty()

    }


}
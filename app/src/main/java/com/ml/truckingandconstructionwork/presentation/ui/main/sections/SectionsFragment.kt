package com.ml.truckingandconstructionwork.presentation.ui.main.sections

import com.ml.truckingandconstructionwork.databinding.FragmentSectionsBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SectionsFragment: BaseFragment<SectionsViewModel,FragmentSectionsBinding>() {
    override val viewModel: SectionsViewModel by viewModel()
    override val binding: FragmentSectionsBinding by viewBinding()

    override fun onView() {
        binding.toolbar.enableLeftItem(true)
    }
}



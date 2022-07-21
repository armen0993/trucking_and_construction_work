package com.ml.truckingandconstructionwork.presentation.ui.main.sections

import com.ml.truckingandconstructionwork.databinding.FragmentSectionsBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SectionsFragment : BaseFragment<SectionsViewModel, FragmentSectionsBinding>() {
    override val viewModel: SectionsViewModel by viewModel()
    override val binding: FragmentSectionsBinding by viewBinding()

    override fun onView() {
        binding.toolbar.enableLeftItem(true)
    }

    override fun onViewClick() {
        with(binding) {
            trucks.setOnClickListener {
                navigateFragment(
                    SectionsFragmentDirections.actionSectionsFragmentToSpecialEquipmentsListFragment(
                        trucks.text.toString()
                    )
                )
            }
            dumpTrucks.setOnClickListener {
                navigateFragment(
                    SectionsFragmentDirections.actionSectionsFragmentToSpecialEquipmentsListFragment(
                        dumpTrucks.text.toString()
                    )
                )
            }
            truckCranes.setOnClickListener {
                navigateFragment(
                    SectionsFragmentDirections.actionSectionsFragmentToSpecialEquipmentsListFragment(
                        truckCranes.text.toString()
                    )
                )
            }
            manipulators.setOnClickListener {
                navigateFragment(
                    SectionsFragmentDirections.actionSectionsFragmentToSpecialEquipmentsListFragment(
                        manipulators.text.toString()
                    )
                )
            }
            crawlerExcavators.setOnClickListener {
                navigateFragment(
                    SectionsFragmentDirections.actionSectionsFragmentToSpecialEquipmentsListFragment(
                        crawlerExcavators.text.toString()
                    )
                )
            }
            wheeledExcavators.setOnClickListener {
                navigateFragment(
                    SectionsFragmentDirections.actionSectionsFragmentToSpecialEquipmentsListFragment(
                        wheeledExcavators.text.toString()
                    )
                )
            }
            excavatorLoaders.setOnClickListener {
                navigateFragment(
                    SectionsFragmentDirections.actionSectionsFragmentToSpecialEquipmentsListFragment(
                        excavatorLoaders.text.toString()
                    )
                )
            }
            concreteMixers.setOnClickListener {
                navigateFragment(
                    SectionsFragmentDirections.actionSectionsFragmentToSpecialEquipmentsListFragment(
                        concreteMixers.text.toString()
                    )
                )
            }
            concretePump.setOnClickListener {
                navigateFragment(
                    SectionsFragmentDirections.actionSectionsFragmentToSpecialEquipmentsListFragment(
                        concretePump.text.toString()
                    )
                )
            }
            towTrucks.setOnClickListener {
                navigateFragment(
                    SectionsFragmentDirections.actionSectionsFragmentToSpecialEquipmentsListFragment(
                        towTrucks.text.toString()
                    )
                )
            }
            loader.setOnClickListener {
                navigateFragment(
                    SectionsFragmentDirections.actionSectionsFragmentToSpecialEquipmentsListFragment(
                        loader.text.toString()
                    )
                )
            }
            roadRoller.setOnClickListener {
                navigateFragment(
                    SectionsFragmentDirections.actionSectionsFragmentToSpecialEquipmentsListFragment(
                        roadRoller.text.toString()
                    )
                )
            }
        }
    }
}



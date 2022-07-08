package com.ml.truckingandconstructionwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ml.truckingandconstructionwork.presentation.extensions.hide
import com.ml.truckingandconstructionwork.presentation.extensions.show
import com.ml.truckingandconstructionwork.presentation.ui.add_work.AddWorkFragment
import com.ml.truckingandconstructionwork.presentation.ui.settings.SettingsFragment
import com.ml.truckingandconstructionwork.databinding.ActivityMainBinding
import com.ml.truckingandconstructionwork.presentation.extensions.getCurrentFragment
import com.ml.truckingandconstructionwork.presentation.ui.offers.OffersListFragment
import com.ml.truckingandconstructionwork.presentation.ui.profile.profile.ProfileFragment
import com.ml.truckingandconstructionwork.presentation.ui.registration.RegistrationFragment

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var exit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(
                fm: FragmentManager,
                f: Fragment,
                v: View,
                savedInstanceState: Bundle?
            ) {
                TransitionManager.beginDelayedTransition(
                    binding.root,
                    Slide(Gravity.BOTTOM).excludeTarget(R.id.nav_host_fragment, true)
                )
                when (f) {
                    is ProfileFragment -> showBottomNav()
                    is OffersListFragment -> showBottomNav()
                    is AddWorkFragment -> showBottomNav()
                    is RegistrationFragment -> showBottomNav()
                    is SettingsFragment -> showBottomNav()
                    else -> hideBottomNav()
                }
            }
        }, true)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        binding.bottomBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profile_fragment -> navController.navigate(R.id.profileFragment)
                R.id.list_offer_fragment -> navController.navigate(R.id.offersListFragment)
                R.id.add_works_fragment -> navController.navigate(R.id.addWorkFragment)
                R.id.registration_fragment -> navController.navigate(R.id.registrationFragment)
                R.id.settings_fragment -> navController.navigate(R.id.settingsFragment)
            }
            return@setOnItemSelectedListener true
        }

        binding.bottomBar.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.list_offer_fragment -> {
                    getCurrentFragment<OffersListFragment>(navHostFragment)?.run {
                        it.apply {
                            this@run.scrollToTop()
                        }
                    }
                }

            }
        }
    }


    override fun onBackPressed() {
        if (exit) {
            finish() // finish activity
        } else {
            exit = true
            Handler().postDelayed({ exit = false }, 3 * 1000)

        }
    }

    private fun showBottomNav() {
        binding.bottomBar.show()
    }

    private fun hideBottomNav() {
        binding.bottomBar.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.ml.truckingandconstructionwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ml.truckingandconstructionwork.presentation.extensions.hide
import com.ml.truckingandconstructionwork.presentation.extensions.show
import com.ml.truckingandconstructionwork.presentation.ui.trucks.TrucksFragment
import com.ml.truckingandconstructionwork.presentation.ui.main.MainFragment
import com.ml.truckingandconstructionwork.presentation.ui.construction_works.ConstructionWorksFragment
import com.ml.truckingandconstructionwork.presentation.ui.settings.SettingsFragment
import com.ml.truckingandconstructionwork.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

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
                    is MainFragment -> showBottomNav()
                    is TrucksFragment -> showBottomNav()
                    is ConstructionWorksFragment -> showBottomNav()
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
                R.id.main_fragment -> navController.navigate(R.id.mainFragment)
                R.id.trucks_fragment -> navController.navigate(R.id.trucksFragment)
                R.id.construction_works_fragment -> navController.navigate(R.id.constructionWorksFragment)
                R.id.settings_fragment -> navController.navigate(R.id.settingsFragment)
            }
            return@setOnItemSelectedListener true
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
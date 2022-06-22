package com.ml.truckingandconstructionwork.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.ml.truckingandconstructionwork.MainActivity
import com.ml.truckingandconstructionwork.presentation.utils.observeInLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<ViewModel : BaseViewModel,ViewBind : ViewBinding>() : Fragment()  {

//    private var _binding: Binding? = null
//    val binding get() = _binding!!

    abstract val viewModel: ViewModel

    abstract val binding: ViewBind


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return binding.root
    }


    protected open fun onView() {}

    protected open fun onViewClick() {}

    protected open fun onEach() {}

    protected inline fun <reified T> onEach(flow: Flow<T>, crossinline action: (T) -> Unit) =
        view?.run {
            if (!this@BaseFragment.isAdded) return@run
            flow.onEach { action(it ?: return@onEach) }.observeInLifecycle(viewLifecycleOwner)
        }

    protected open lateinit var navController: NavController

    private lateinit var  navOptions: NavOptions

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onEach()
        onView()
        onViewClick()


        onEach(viewModel.showDefaultError) {
            it?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
        }


        navController = Navigation.findNavController(view)
        navOptions = NavOptions.Builder()
            .setPopUpTo(navController.graph.startDestinationId, true)
            .setLaunchSingleTop(true)
            .build()
    }



    protected fun popBackStack() {
        navController.popBackStack()
    }

    protected fun navigateFragment(destinationId: Int, arg: Bundle? = null) {
        navController.navigate(destinationId, arg)
    }

    protected fun navigateRootFragment(destinationId: Int, arg: Bundle? = null) {
        navController.navigate(destinationId, arg, navOptions)
    }

    protected fun navigateFragment(destinations: NavDirections) {
        navController.navigate(destinations)
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}
package com.ml.truckingandconstructionwork.presentation.utils

import androidx.navigation.NavController

fun NavController.isFragmentInBackStack(destinationId: Int) =
    try {
        getBackStackEntry(destinationId)
        true
    } catch (e: Exception) {
        //Log.e("", e.localizedMessage)
        false
    }

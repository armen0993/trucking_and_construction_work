package com.ml.truckingandconstructionwork.presentation.utils

import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.Flow

inline fun <reified T> Flow<T>.observeInLifecycle(
    lifecycleOwner: LifecycleOwner
) = FlowObserver(lifecycleOwner, this)


package com.ml.truckingandconstructionwork.data.app_service

import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails

interface PreferenceService {

    fun setUserDetails(userDetails: UserDetails)
    fun getUserDetails(): UserDetails

    fun cleraUserDetails()
}
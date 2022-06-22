package com.ml.truckingandconstructionwork.domain.models
import com.ml.truckingandconstructionwork.data.models.UserDetailsModel

data class UserDetails(
    val userid:Int=-1,
    val gender:String ="",
    val name: String = "",
    val surname: String ="",
    val dataOfBirth:String ="",
    val city: String ="",
    val email: String ="",
    val phoneNumber: String ="",
    val login: String ="",
    val password: String ="",
    val clientType: String =""
) {
    companion object {
        fun from(data: UserDetailsModel) =
            UserDetails(
                data.userId?:-1,
                data.gender?:"",
                data.name ?: "",
                data.surname ?: "",
                data.dataOfBirth?:"",
                data.city ?: "",
                data.email ?: "",
                data.phoneNumber ?: "",
                data.login ?: "",
                data.password ?: "",
                data.clientType ?: ""
            )
    }
}

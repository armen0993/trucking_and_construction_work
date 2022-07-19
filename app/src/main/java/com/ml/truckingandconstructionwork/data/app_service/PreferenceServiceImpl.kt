package com.ml.truckingandconstructionwork.data.app_service

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.ml.truckingandconstructionwork.data.models.registration.UserDetailsModel
import com.ml.truckingandconstructionwork.domain.models.registration.UserDetails

class PreferenceServiceImpl(private val context: Context):PreferenceService {

    private val USER_DETAILS = "USER_DETAILS"
    private val SKIPPED = "SKIPPED"

    val sharedPreferences: SharedPreferences = //PreferenceManager.getDefaultSharedPreferences(context)
        context.getSharedPreferences(context.packageName + "_preferences", Context.MODE_PRIVATE)

    inline fun <reified T> get(key: String): T? {
        val value = sharedPreferences.getString(key, null)
        val gson = Gson()
        return gson.fromJson(value, T::class.java)
    }

    inline fun <reified T> put(data: T, key: String) {
        val gson = Gson()
        val jsonString = gson.toJson(data)
        return sharedPreferences.set(key, jsonString)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is Int -> edit { it.putInt(key, value) }
            is Long -> edit { it.putLong(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is String -> edit { it.putString(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            else -> throw UnsupportedOperationException("Not implemented type")
        }
    }

    inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T): T {
        return when (T::class) {
            Int::class -> getInt(key, defaultValue as Int) as T
            Long::class -> getLong(key, defaultValue as Long) as T
            Float::class -> getFloat(key, defaultValue as Float) as T
            String::class -> getString(key, defaultValue as String) as T
            Boolean::class -> getBoolean(key, defaultValue as Boolean) as T
            else -> throw UnsupportedOperationException("Not implemented type")
        }
    }
  //    private val versionName: String
    //        get() {
//            try {
//                val pInfo: PackageInfo =
//                    context.packageManager.getPackageInfo(context.packageName, 0)
//                return pInfo.versionName
//            } catch (e: PackageManager.NameNotFoundException) {
//                e.printStackTrace()
//            }
//            return ""
//        }

//    override fun setUserDetails(userDetails: UserDetailsModel) {
//       sharedPreferences[USER_DETAILS] = userDetails
//    }
//
    override fun setUserDetails(userDetails: UserDetails) {
       put(userDetails,USER_DETAILS)
    }

    override fun getUserDetails(): UserDetails {
        return get<UserDetails>(USER_DETAILS) ?: UserDetails()
    }

    override fun clearUserDetails() {
        put(UserDetails(),USER_DETAILS)
    }

    override fun setSkipped(skipped: Boolean) {
        put(skipped,SKIPPED)
    }

    override fun getSkipped(): Boolean {
        return get<Boolean>(SKIPPED)?:false
    }


}
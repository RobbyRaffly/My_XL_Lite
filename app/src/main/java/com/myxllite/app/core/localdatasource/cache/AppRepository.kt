package com.myxllite.app.core.localdatasource.cache

import android.content.Context
import android.content.SharedPreferences
import com.myxllite.app.kit.constant.CacheConstant

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
class AppRepository(private val context: Context) : AppPreferences {

    companion object {
        // contain pair of key and default value
        private val IN_APP_UPDATE_SHOW = Pair("IN_APP_UPDATE_SHOW", false)
        private val AVATAR_SIGNATURE = Pair("AVATAR_SIGNATURE", "")

        const val SHARED_PREFERENCE_NAME = "XL_ULTIMATE_CACHE"
        const val SHARED_PREFERENCE_NAME_UNCLEARABLE = "XL_ULTIMATE_CACHE_UNCLEARABLE"
        const val SHARED_PREFERENCE_NAME_NETWORK = "XL_ULTIMATE_CACHE_NETWORK"
        const val SHARED_PREFERENCE_NAME_CACHE_FAIL_SAFE = "XL_ULTIMATE_CACHE_FAIL_SAFE"
        const val SHARED_PREFERENCE_NAME_USER_SESSION = "XL_ULTIMATE_CACHE_USER_SESSION"
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    private fun getDefaultPreferences(): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    private fun getUnclearAblePreferences(): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCE_NAME_UNCLEARABLE, Context.MODE_PRIVATE)
    }

    private fun getNetworkPreferences(): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCE_NAME_NETWORK, Context.MODE_PRIVATE)
    }

    private fun getFailSafePreferences(): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCE_NAME_CACHE_FAIL_SAFE, Context.MODE_PRIVATE)
    }

    private fun getUserSessionPreferences(): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCE_NAME_USER_SESSION, Context.MODE_PRIVATE)
    }

    override fun clearDefaultPreferences() {
        getDefaultPreferences().edit().clear().apply()
    }

    override fun clearUnClearAblePreferences() {
        getUnclearAblePreferences().edit().clear().apply()
    }

    override fun clearNetworkPreferences() {
        getNetworkPreferences().edit().clear().apply()
    }

    override fun clearFailSafePreferences() {
        getFailSafePreferences().edit().clear().apply()
    }

    override fun clearUserSessionPreferences() {
        getUserSessionPreferences().edit().clear().apply()
    }

    override var isInAppUpdateShow: Boolean
        get() = getUnclearAblePreferences().getBoolean(IN_APP_UPDATE_SHOW.first, IN_APP_UPDATE_SHOW.second)
        set(value) = getUnclearAblePreferences().edit {
            it.putBoolean(IN_APP_UPDATE_SHOW.first, value)
        }

    override var avatarSignature: String
        get() = getNetworkPreferences().getString(AVATAR_SIGNATURE.first, AVATAR_SIGNATURE.second).orEmpty()
        set(value) = getNetworkPreferences().edit(){
            it.putString(AVATAR_SIGNATURE.first, value)
        }

}
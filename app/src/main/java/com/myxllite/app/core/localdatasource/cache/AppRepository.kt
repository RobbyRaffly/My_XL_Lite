package com.myxllite.app.core.localdatasource.cache

import android.content.Context
import android.content.SharedPreferences
import com.myxllite.app.kit.constant.CacheConstant

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
class AppRepository(private val preferences: SharedPreferences) : AppPreferences {

    companion object {
        // contain pair of key and default value
        private val PROFILE = Pair("KEY_PROFILE", "")
        private val IN_APP_UPDATE_SHOW = Pair("IN_APP_UPDATE_SHOW", false)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    override var profile: String
        get() = preferences.getString(PROFILE.first, PROFILE.second).orEmpty()
        set(value) = preferences.edit {
            it.putString(PROFILE.first, value)
        }

    override var isInAppUpdateShow: Boolean
        get() = preferences.getBoolean(IN_APP_UPDATE_SHOW.first, IN_APP_UPDATE_SHOW.second)
        set(value) = preferences.edit{
            it.putBoolean(IN_APP_UPDATE_SHOW.first, value)
        }
}
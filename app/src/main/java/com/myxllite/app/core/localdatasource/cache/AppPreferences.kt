package com.myxllite.app.core.localdatasource.cache

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
interface AppPreferences {

    fun clearDefaultPreferences()

    fun clearUnClearAblePreferences()

    fun clearNetworkPreferences()

    fun clearFailSafePreferences()

    fun clearUserSessionPreferences()

    var isInAppUpdateShow: Boolean

    var avatarSignature: String
}
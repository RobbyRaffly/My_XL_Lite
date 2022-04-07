package com.myxllite.app.domain.interactor.splashscreen

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
interface SplashScreenInteractor {
    fun setInAppUpdateShow(isShow: Boolean)

    fun clearNetworkAndFailSafeCache()

    fun resetAvatarSignature(value: String)

    suspend fun deleteAllStoreConfig(): Int
}
package com.myxllite.app.domain.repository.local

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
interface LocalDataSource {
    fun setInAppUpdateShow(isShow: Boolean)

    fun clearNetworkAndFailSafeCache()

    fun resetAvatarSignature(value: String)

    suspend fun deleteAllStoreConfig(): Int
}
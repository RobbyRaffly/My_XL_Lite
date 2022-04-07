package com.myxllite.app.domain.repository.local

import com.google.gson.Gson
import com.myxllite.app.core.localdatasource.cache.AppPreferences
import com.myxllite.app.core.localdatasource.roomdb.AppDatabase
import javax.inject.Inject

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
class LocalRepository @Inject constructor(
    private val appPreferences: AppPreferences,
    private val appDatabase: AppDatabase,
    private val gson: Gson
) : LocalDataSource {

    override fun setInAppUpdateShow(isShow: Boolean) {
        appPreferences.isInAppUpdateShow = isShow
    }

    override fun clearNetworkAndFailSafeCache() {
        appPreferences.clearNetworkPreferences()
        appPreferences.clearFailSafePreferences()
    }

    override fun resetAvatarSignature(value: String) {
        appPreferences.avatarSignature = value
    }

    override suspend fun deleteAllStoreConfig(): Int {
        return appDatabase.storeConfigDao().deleteAll()
    }
}
package com.myxllite.app.core.localdatasource

import android.content.Context
import androidx.room.Room
import com.myxllite.app.BuildConfig
import com.myxllite.app.core.localdatasource.cache.AppPreferences
import com.myxllite.app.core.localdatasource.cache.AppRepository
import com.myxllite.app.core.localdatasource.roomdb.AppDatabase

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
object LocalDataSourceProvider {
    private var appDatabase: AppDatabase? = null
    private var appPreferences: AppPreferences? = null

    @JvmStatic
    @Synchronized
    fun getAppDatabase(context: Context): AppDatabase {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                BuildConfig.DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
        }
        return appDatabase as AppDatabase
    }

    @JvmStatic
    @Synchronized
    fun getAppPreferences(context: Context): AppPreferences {
        if (appPreferences == null) {
            appPreferences =
                AppRepository(
                    context.getSharedPreferences(
                        BuildConfig.PREFERENCES_NAME,
                        Context.MODE_PRIVATE
                    )
                )
        }
        return appPreferences as AppPreferences
    }
}
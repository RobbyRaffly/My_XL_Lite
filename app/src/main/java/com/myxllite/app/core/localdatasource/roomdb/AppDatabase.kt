package com.myxllite.app.core.localdatasource.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myxllite.app.core.localdatasource.roomdb.dao.ProfileDao
import com.myxllite.app.core.localdatasource.roomdb.table.ProfileTable

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
@Database(
    entities = [ProfileTable::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao
}
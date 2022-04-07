package com.myxllite.app.core.localdatasource.roomdb.dao

import androidx.room.*
import com.myxllite.app.core.localdatasource.roomdb.table.StoreConfigTable

/**
 * Created by Bacharudin Farisi on 31/03/22
 */
@Dao
interface StoreConfigDao {
    @Query("SELECT * FROM store_config")
    suspend fun getAll(): List<StoreConfigTable>

    @Query("SELECT * FROM store_config WHERE subscriber_id = :subscriberId LIMIT 1")
    suspend fun findBySubscriberId(subscriberId: String): StoreConfigTable?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg storeConfigs: StoreConfigTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(storeConfig: StoreConfigTable)

    @Delete
    suspend fun delete(storeConfig: StoreConfigTable)

    @Query("DELETE FROM store_config WHERE subscriber_id = :subscriberId")
    suspend fun deleteBySubscriberId(subscriberId: String)

    @Query("DELETE FROM store_config")
    suspend fun deleteAll(): Int
}
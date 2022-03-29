package com.myxllite.app.core.localdatasource.roomdb.dao

import androidx.room.*
import com.myxllite.app.core.localdatasource.roomdb.table.ProfileTable

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile")
    suspend fun getAll(): List<ProfileTable>

    @Query("SELECT * FROM profile WHERE subscriber_id = :subscriberId LIMIT 1")
    suspend fun findBySubscriberId(subscriberId: String): ProfileTable?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg profiles: ProfileTable)

    @Delete
    suspend fun delete(profile: ProfileTable)

    @Query("DELETE FROM profile")
    suspend fun deleteAll()
}
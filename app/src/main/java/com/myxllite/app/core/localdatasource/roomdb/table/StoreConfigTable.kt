package com.myxllite.app.core.localdatasource.roomdb.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Bacharudin Farisi on 31/03/22
 */

@Entity(tableName = "store_config")
data class StoreConfigTable(
    @PrimaryKey
    @ColumnInfo(name = "subscriber_id") val subscriberId: String,
    @ColumnInfo(name = "config") val config: String?,
    @ColumnInfo(name = "last_updated_at") val lastUpdatedAt: Long?
)

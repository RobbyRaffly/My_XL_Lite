package com.myxllite.app.core.localdatasource.roomdb.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
@Entity(tableName = "profile")
data class ProfileTable(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "msisdn") val msisdn: String,
    @ColumnInfo(name = "subscription_type") val subscriptionType: String,
    @PrimaryKey @ColumnInfo(name = "subscriber_id") val subscriberId: String,
    @ColumnInfo(name = "avatar") val avatar: String,
    @ColumnInfo(name = "registered_address") val registeredAddress: String,
    @ColumnInfo(name = "registered_name") val registeredName: String,
    @ColumnInfo(name = "dob") val dob: String?,
    @ColumnInfo(name = "dob_modified") val isDobModified: Boolean,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "alternate_phone_number") val alternatePhoneNumber: String,
    @ColumnInfo(name = "day_to_birthday") val dayToBirthday: Int,
    @ColumnInfo(name = "age") val age: Int
)

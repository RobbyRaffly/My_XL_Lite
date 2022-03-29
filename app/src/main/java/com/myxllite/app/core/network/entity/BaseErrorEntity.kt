package com.myxllite.app.core.network.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
data class BaseErrorEntity(
    @SerializedName("code") val errorCode: String?,
    @SerializedName("message") val errorMessage: String?
)
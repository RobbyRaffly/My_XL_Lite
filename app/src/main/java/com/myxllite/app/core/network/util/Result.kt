package com.myxllite.app.core.network.util

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
sealed class Result<out T : Any> {

    class Success<out T : Any>(val data: T) : Result<T>()

    class Error(val error: ErrorDetail) : Result<Nothing>()
}

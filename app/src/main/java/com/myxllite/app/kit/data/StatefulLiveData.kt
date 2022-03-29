package com.myxllite.app.kit.data

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
open class StatefulLiveData<T> {
    class Success<T>(val data: T) : StatefulLiveData<T>()
    class Error<T>(val error: T) : StatefulLiveData<T>()
}
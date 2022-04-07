package com.myxllite.app.kit.data

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
abstract class StatefulLiveData<T> {
    class Success<T>(val data: T) : StatefulLiveData<T>()
    class Error<T>(val ErrorDetail: T) : StatefulLiveData<T>()
}